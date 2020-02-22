package com.destiny.api.config.annotation;

import com.destiny.api.domain.base.Result;
import com.destiny.api.domain.vo.Response;

import java.lang.annotation.*;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ResponseResult {
    Class<? extends Result> value() default Response.class;
}
