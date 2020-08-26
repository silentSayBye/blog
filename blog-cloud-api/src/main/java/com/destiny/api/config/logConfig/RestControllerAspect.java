package com.destiny.api.config.logConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.destiny.api.exception.GlobalExceptionHandler;
import com.destiny.api.util.RequestContextUtil;
import com.google.common.collect.Lists;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName RestControllerAspect
 * @Author Administrator
 * @Date 2019/7/2321:09
 * @Version 1.0
 **/

@Slf4j
@Aspect
@Component
public class RestControllerAspect {

    @Around("@within(org.springframework.web.bind.annotation.RestController) || @annotation(org.springframework.web.bind.annotation.RestController)")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Boolean flag = needLog(method);
        if (!flag){
            return joinPoint.proceed();
        }
        HttpServletRequest request = RequestContextUtil.getRequest();
        String uri = request.getRequestURI();
        String methodName = getMethodName(joinPoint);
        String params = getParams(joinPoint);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("start requst ============ methodName ={},uri ={},params ={}",methodName,uri,params);
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        log.info("end response ============ {},costTime = {}ms",proceed ,stopWatch.getTime());
        return proceed;
    }

    private Boolean needLog(Method method){
        return true;
    }

    private String getMethodName(ProceedingJoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();
        String temp = "(**)";
        if (methodName.endsWith(temp)){
            methodName.substring(0,methodName.length()-temp.length());
        }
        return methodName;
    }

    private String getParams(ProceedingJoinPoint joinPoint){
        Object[] params = joinPoint.getArgs();
        StringBuffer sb = new StringBuffer();
        Arrays.stream(params).forEach(param ->{
            if (param instanceof HttpRequest){
                param = HttpRequest.class.getSimpleName();
            } else if (param instanceof HttpResponse){
                param = HttpResponse.class.getSimpleName();
            } else if (param instanceof MultipartFile){
                Long size = ((MultipartFile) param).getSize();
                param = MultipartFile.class.getSimpleName()+size;
            }else {
                param = changeSensitiveFields(param);
            }
           sb.append(param).append(',');
        });

        return sb.deleteCharAt(sb.length()-1).toString();
    }


    private String changeSensitiveFields(Object obj){
        JSONObject jsonObject = new JSONObject();
        if (obj == null || obj instanceof Exception){
            return jsonObject.toJSONString();
        }
        String params = JSON.toJSONString(obj);
        JSONObject object = JSONObject.parseObject(params);
        List<String> sensitiveFieldList = getSensitiveFieldList();
        for (String sensitiveField : sensitiveFieldList) {
            if (object.containsKey(sensitiveField)) {
                object.put(sensitiveField, "******");
            }
        }
        return object.toJSONString();
    }

    private List<String> getSensitiveFieldList(){
        List sensitiveFieldList = Lists.newArrayList();
        sensitiveFieldList.add("pwd");
        sensitiveFieldList.add("password");
        return sensitiveFieldList;
    }

}
