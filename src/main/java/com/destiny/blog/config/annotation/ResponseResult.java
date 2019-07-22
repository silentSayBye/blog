package com.destiny.blog.config.annotation;

import com.destiny.blog.domain.base.Result;
import com.destiny.blog.domain.vo.Response;

import java.lang.annotation.*;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ResponseResult {
    Class<? extends Result> value() default Response.class;
}
