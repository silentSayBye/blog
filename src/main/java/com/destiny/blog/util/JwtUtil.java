package com.destiny.blog.util;

import com.alibaba.fastjson.JSON;
import com.destiny.blog.domain.dto.UserDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.jsonwebtoken.*;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;


/**
 * @ClassName Jwt
 * @Author Administrator
 * @Date 2019/6/22 14:56
 * @Version 1.0
 **/
@Slf4j
public class JwtUtil {

    private static final String CLAIM_KEY_AUTHORITIES = "socpe";
    private static final String CLAIM_KRY_USER_ID = "userId";
    private static final String ROLE_REFRESH_TOKEN ="ROLE_REFRESH_TOKEN";

    private Map<String,Object> tokenMap = new ConcurrentHashMap<>(20);

    @Value("${jwt.secure}")
    private String secure;

    @Value("${jwt.expire}")
    private Long expiration;

    @Value("${jwt.expire}")
    private Long refreshExpiration;

    private final  SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


    public String generalAccessToken(UserDto userDto){
        Map<String, Object> claims = setClaims(userDto);
        claims.put(CLAIM_KEY_AUTHORITIES,authoritiesToArray(userDto.getAuthorities()).get(0));
        return  generateAccessToken(userDto.getUsername(),claims);
    }

    public Boolean canTokenBeRefresh(String token){
        Date createdDate = getCreatedDateFromToken(token);
        return (!isTokenExpiration(token));
    }

    public String refreshToken(String token){
        Claims claims = parseToken(token);
        return generalRefreshToken(claims.getSubject(),claims);
    }
    public Boolean validToken(UserDto userDto,String token){
        Integer userId = getUserIdFromToken(token);
        String username = getUsernameFromToken(token);
        return (username.equals(userDto.getUsername())
                && userId.equals(userDto.getUsername())
                && ! isTokenExpiration(token));
    }

    public Integer getUserIdFromToken(String token){
        Claims claims = parseToken(token);
        return  (Integer) claims.get(CLAIM_KRY_USER_ID);
    }

    public String getUsernameFromToken(String token){
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    public String generateRefreshToken(UserDto userDto){
        Map<String, Object> claims = setClaims(userDto);
        String[] roles = {ROLE_REFRESH_TOKEN};
        claims.put(CLAIM_KEY_AUTHORITIES, JSON.toJSON(roles));
        return generalRefreshToken(userDto.getUsername(),claims);
    }

    public void deleteTokn(String username){
        if (StringUtils.isNotBlank(username)){
            tokenMap.remove(username);
        }
    }

    public void putToken(String username,String token){
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(token)){
            tokenMap.put(username,token);
        }
    }

    public Boolean containToken(String username,String token){
        if (StringUtils.isNotBlank(username)){
            if (tokenMap.containsKey(username) && tokenMap.get(username).equals(token)){
                return true;
            }
        }
        return false;
    }

    private Claims parseToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secure)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.info("claim parse failure");
        }
        return claims ;
    }

    private Date getExpirationFromToken(String token){
        Claims claims = parseToken(token);
        Date date = null;
        if (claims != null){
            date = claims.getExpiration();
        }
        return date;
    }

    private Date getCreatedDateFromToken(String token){
        Claims claims = parseToken(token);
        Date date = null;
        if (claims != null){
            date = claims.getIssuedAt();
        }
        return date;
    }

    private Boolean isTokenExpiration(String token){
        Date expiration = getExpirationFromToken(token);
        return expiration.before(new Date());
    }


    private Map<String,Object> setClaims(UserDto userDto){
        Map claims = Maps.newHashMap();
        claims.put(CLAIM_KRY_USER_ID,userDto.getId());
        return claims;
    }

    private List authoritiesToArray(Collection<? extends GrantedAuthority> authorities){
        List list = java.util.Collections.EMPTY_LIST;
        if (Collections.isEmpty(authorities)){
            return list;
        }
        list = Lists.newArrayList();
        for (GrantedAuthority authority : authorities){
            list.add(authority.getAuthority());
        }
        return list;
    }
    private String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, expiration);
    }

    private String generalRefreshToken(String subject,Map<String,Object> claims){
        return generateToken(subject,claims,refreshExpiration);
    }

    private  String generateToken(String subject, Map<String,Object> claims, Long expiration){
      return Jwts.builder().setId(UUID.randomUUID().toString())
              .setIssuedAt(new Date())
              .setExpiration(generateExpirationDate())
              .setClaims(claims)
              .setSubject(subject)
              .signWith(signatureAlgorithm,secure)
              .compact();
    }
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+ expiration * 1000);
    }

    public static void main(String[] args) {
        Map map = Maps.newHashMap();
        map.put("aa",11);
        map.put("aa",12);
        map.forEach((a,b) -> {
            System.out.println(a +"111"+ b);
        });
    }
}
