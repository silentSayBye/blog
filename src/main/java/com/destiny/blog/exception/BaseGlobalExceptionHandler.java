package com.destiny.blog.exception;

import com.destiny.blog.domain.enums.ResponseCode;
import com.destiny.blog.domain.enums.ResponseStatus;
import com.destiny.blog.domain.vo.DefaultErrorResult;
import com.destiny.blog.util.DateUtils;
import com.destiny.blog.util.RequestContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName BaseGlobalException
 * @Author Administrator
 * @Date 2019/7/2221:55
 * @Version 1.0
 **/

@Slf4j
public class BaseGlobalExceptionHandler {

    /**
     * 处理验证参数封装错误时异常
     */
    protected ResponseEntity<DefaultErrorResult> handleConstraintViolationException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.info("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
        DefaultErrorResult defaultErrorResult = DefaultErrorResult.builder()
                .code(Integer.valueOf(ResponseCode.FAILED.getCode()))
                .timestamp(DateUtils.currentDateTime())
                .message(e.getMessage())
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
