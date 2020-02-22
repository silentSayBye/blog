package com.destiny.api.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfig{

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,? extends Object> redisTemplate =  new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jsonRedisSerializer.setObjectMapper(setObjectMapper());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        log.info(">>>> custom redis cache manager start <<<<");
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        // 指定key value的序列化类型
        Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jsonSerializer.setObjectMapper(setObjectMapper());
        RedisSerializationContext.SerializationPair<Object> jsonSerializationPair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);
        RedisSerializationContext.SerializationPair<String> stringSerializationPair = RedisSerializationContext.SerializationPair
                .fromSerializer(RedisSerializer.string());

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeKeysWith(stringSerializationPair)
                .serializeValuesWith(jsonSerializationPair)
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(100));

        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);

        return redisCacheManager;
    }

    private ObjectMapper setObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return objectMapper;
    }
}
