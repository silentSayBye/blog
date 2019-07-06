package com.destiny.blog.util;

import com.destiny.blog.domain.dto.JwtResult;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;


/**
 * @ClassName Jwt
 * @Author Administrator
 * @Date 2019/6/22 14:56
 * @Version 1.0
 **/
public class JwtUtil {

    private static final String privateKey = "25eb35fe-c5fc-47d3-958c-2056bd900245";
    public static String createJwt( String id, String subject, String user, Long expire){
        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
        long now = System.currentTimeMillis();
        SecretKey secretKey = generalKey();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer(user)
                .setIssuedAt(new Date(now))
                .signWith(sa,secretKey);
        if (expire > 0){
            Date expireDate = new Date(expire + now);
            jwtBuilder.setExpiration(expireDate);
        }
        return jwtBuilder.compact();
    }

    public static JwtResult validateJWT(String jwt){
        Claims claims =null;
        claims = parseJwt(jwt);
        JwtResult jwtResult = null;
        try {
            jwtResult = JwtResult
                    .builder()
                    .result(true)
                    .code(HttpStatus.OK)
                    .claims(claims)
                    .message("验证成功")
                    .build();

        } catch (ExpiredJwtException e){
            jwtResult = JwtResult
                    .builder()
                    .result(true)
                    .code(HttpStatus.INTERNAL_SERVER_ERROR)
                    .claims(claims)
                    .message("token已失效")
                    .build();
        } catch (SignatureException e){
            jwtResult = JwtResult
                    .builder()
                    .result(true)
                    .code(HttpStatus.INTERNAL_SERVER_ERROR)
                    .claims(claims)
                    .message("签名失败")
                    .build();
        } catch (Exception e){
            jwtResult = JwtResult
                    .builder()
                    .result(true)
                    .code(HttpStatus.INTERNAL_SERVER_ERROR)
                    .claims(claims)
                    .message("验证失败")
                    .build();
        }
        return jwtResult;
    }

    public static Claims parseJwt(String jwt){
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    private  static  SecretKey generalKey(){
        byte[] key = Base64.decodeBase64(privateKey);
        SecretKey secretKey = new SecretKeySpec(key,0,key.length,"ASE");
        return secretKey;
    }

    public static void main(String[] args) {
//        JwtUtil jwtUtil = new JwtUtil();
//        System.out.println( jwtUtil.privateKey);
//        System.out.println(jwtUtil.generalKey().getEncoded().toString());
        System.out.println(new Date());
        System.out.println(new Date(System.currentTimeMillis()+3600000));
    }
}
