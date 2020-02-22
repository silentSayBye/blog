package com.destiny.api.exception;

import com.destiny.api.domain.enums.ResponseCode;
import com.destiny.api.domain.vo.DefaultErrorResult;
import com.destiny.api.util.DateUtils;
import com.destiny.api.util.RequestContextUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Date 2019/7/2221:55
 * @Version 1.0
 **/

@Slf4j
public class BaseGlobalExceptionHandler {

    /**
     * 处理验证参数封装错误时异常
     */
    protected ResponseEntity<DefaultErrorResult> handleConstraintViolationException(Exception e, HttpServletRequest request) {
        String message = e.getMessage();
        if (e instanceof MethodArgumentNotValidException){
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<String> errorMessages = Lists.newArrayList();
            String errorMessage;
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error: allErrors){
                if (error instanceof FieldError){
                    errorMessage = String.format("%s对象的%s字段%s",error.getObjectName(),((FieldError)error).getField(),error.getDefaultMessage());
                    errorMessages.add(errorMessage);
                }else {
                    errorMessage = String.format("%s对象%s",error.getObjectName(),error.getDefaultMessage());
                    errorMessages.add(errorMessage);
                }
            }
            message = String.format("field valid failed: %s",StringUtils.join(errorMessages,","));
        }
        log.info("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
        DefaultErrorResult defaultErrorResult = DefaultErrorResult.builder()
                .code(Integer.valueOf(ResponseCode.FAILED.getCode()))
                .timestamp(DateUtils.currentDateTime())
                .message(message)
                .exception(e.getClass().getName())
                .path(RequestContextUtil.getRequest().getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(defaultErrorResult);
    }

    /**
     * 处理通用自定义业务异常
     */
    protected ResponseEntity<DefaultErrorResult> handleBusinessException(CustomException e, HttpServletRequest request) {
        log.info("handleBusinessException start, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());
        DefaultErrorResult defaultErrorResult =
                DefaultErrorResult.builder()
                        .code(Integer.valueOf(ResponseCode.FAILED.getCode()))
                        .timestamp(DateUtils.currentDateTime())
                        .message(e.getMessage())
                        .exception(e.getClass().getName())
                        .path(RequestContextUtil.getRequest().getRequestURI())
                        .build();
        return ResponseEntity
                .status(e.getStatus())
                .body(defaultErrorResult);
    }

    /**
     * 处理运行时系统异常
     */
    protected ResponseEntity<DefaultErrorResult> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("handleRuntimeException start, uri:{}, caused by: ", request.getRequestURI(), e);
        DefaultErrorResult defaultErrorResult = DefaultErrorResult.builder()
                .code(Integer.valueOf(ResponseCode.FAILED.getCode()))
                .timestamp(DateUtils.currentDateTime())
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .path(RequestContextUtil.getRequest().getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(defaultErrorResult);
    }

}
