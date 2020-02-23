package com.blog.security.exception;

import lombok.Data;
import lombok.ToString;

/**
 * @Date 2020/2/23 16:21
 * @Version 1.0
 **/

@Data
@ToString
public class FailFetchInfoByFeignException extends RuntimeException{

    private String message;

    public FailFetchInfoByFeignException() {}

    public FailFetchInfoByFeignException(String message) {
       this.message = message;
    }
}
