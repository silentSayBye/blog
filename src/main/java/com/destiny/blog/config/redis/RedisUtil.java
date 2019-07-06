package com.destiny.blog.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public final class RedisUtil {

    @Autowired
    private static RedisTemplate<String,Object> redisTemplate;

    public static boolean expire(String key, long time, TimeUnit unit){
        return redisTemplate.expire(key,time,unit);
    }

    public static boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

}
