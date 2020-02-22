package com.destiny.api.exception;

import com.destiny.api.domain.vo.DefaultErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobalExceptionHandler
 * @Author Administrator
 * @Date 2019/7/2222:18
 * @Version 1.0
 **/

@ControllerAdvice
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler{

    @ExceptionHandler(value = {HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class})
    protected ResponseEntity<DefaultErrorResult> handleConstraintViolationException(Exception e, HttpServletRequest request) {
        return super.handleConstraintViolationException(e, request);
    }

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<DefaultErrorResult> handleBusinessException(CustomException e, HttpServletRequest request) {
        return super.handleBusinessException(e, request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<DefaultErrorResult> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        return super.handleRuntimeException(e, request);
    }
}
