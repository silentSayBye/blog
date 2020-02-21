package com.destiny.blog.util;


import com.google.common.collect.Maps;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


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

    private Map<String,Object> tokenMap = new ConcurrentHashMap<>(20);

    @Value("${jwt.secure}")
    private String secure;

    @Value("${jwt.expire}")
    private Long expiration;

    @Value("${jwt.expire}")
    private Long refreshExpiration;

    private final  SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;


    /**
     * @Author Administrator
     * 生成token
     * @Author Administrator
     * 生成token
     **/
    public String generalAccessToken(UserDetails userDto){
        Map<String, Object> claims = setClaims(userDto);
//        claims.put(CLAIM_KEY_AUTHORITIES,authoritiesToArray(userDto.getAuthorities()).get(0));
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
     * @Description token是否可刷新
     **/
//    public Boolean canRefreshToken(String token){
//        Date expiration = getExpirationFromToken(token);
//        return expiration.before(new Date());
//    }

    /**
     * @Description 验证token
     **/
    public Boolean validToken(UserDetails userDto, String token){
        String username = getUsernameFromToken(token);
        return username.equals(userDto.getUsername());
//        return (username.equals(userDto.getUsername())
//                && isTokenExpiration(token));
    }


    public String getUsernameFromToken(String token){
        Claims claims = parseToken(token);
        return (String)claims.get(CLAIM_KEY_USER);
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
            log.info("token is {}",claims);
        }catch (Exception e){
            log.info("claim parse failure");
        }
        return claims ;
    }

//    private Date getExpirationFromToken(String token){
//        Claims claims = parseToken(token);
//        Date date = null;
//        if (claims != null){
//            date = claims.getExpiration();
//        }
//        return date;
//    }

//   private Date getCreatedDateFromToken(String token){
//        Claims claims = parseToken(token);
//        Date date = null;
//        if (claims != null){
//            date = claims.getIssuedAt();
//        }
//        return date;
//    }

//    private Boolean isTokenExpiration(String token){
//        Date expiration = getExpirationFromToken(token);
//        return expiration.before(new Date());
//    }


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

//    private List authoritiesToArray(Collection<? extends GrantedAuthority> authorities){
//        return authorities.stream().map(authority -> authority.getAuthority())
//                .collect(Collectors.toList());
//    }

//    public static void main(String[] args) {
//       Date now = new Date();
//
//        long after = now.getTime() + 1000;
//        System.out.println(now.before(new Date(after)));
//
//    }
}
