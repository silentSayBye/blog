package com.blog.security.utils;


import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @ClassName Jwt
 * @Author Administrator
 * @Date 2019/6/22 14:56
 * @Version 1.0
 **/
@Slf4j
@Component
public class JwtUtil {

    private static final String CLAIM_KEY_USER = "username";

    @Value("${jwt.secure}")
    private String secure;

    @Value("${jwt.expire}")
    private Long expiration;

    @Value("${jwt.expire}")
    private Long refreshExpiration;

    @Autowired
    private RedisTemplate redisTemplate;

    private final  SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;


    /**
     * @Author Administrator
     * 生成token
     * @Author Administrator
     * 生成token
     **/
    public String generalAccessToken(UserDetails userDto){
        Map<String, Object> claims = setClaims(userDto);
        return  generateToken(claims);
    }

    /**
     * @Author Administrator
     * 刷新token
     **/
    public String refreshToken(String token){
        Claims claims = parseToken(token);
        return generateToken(claims);
    }

    /**
     * @Description 验证token
     **/
    public Boolean validToken(UserDetails userDto, String token){
        String username = getUsernameFromToken(token);
        return username.equals(userDto.getUsername());
    }


    public String getUsernameFromToken(String token){
        Claims claims = parseToken(token);
        return (String)claims.get(CLAIM_KEY_USER);
    }


    private Claims parseToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secure)
                    .parseClaimsJws(token)
                    .getBody();
            log.info("token is {}",claims);
        }catch (Exception e){
            log.error("claim parse failure");
        }
        return claims ;
    }

    private Map<String,Object> setClaims(UserDetails userDto){
        Map claims = Maps.newHashMap();
        claims.put(CLAIM_KEY_USER,userDto.getUsername());
        return claims;
    }

    private  String generateToken(Map<String,Object> claims){
      return Jwts.builder().setId(UUID.randomUUID().toString())
              .setIssuedAt(new Date())
              .setExpiration(generateExpirationDate())
              .setClaims(claims)
              .signWith(signatureAlgorithm,secure)
              .compact();
    }
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+ expiration * 1000);
    }
}
