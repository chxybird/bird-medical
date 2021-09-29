package com.bird.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @Author lipu
 * @Date 2020/12/18 20:29
 * @Description JWT工具类
 */
public class JwtUtils {
    /**
     * 认证头部的KEY标识
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * Token有效时间 8小时
     */
    public static final long TIME = 1000 * 60 * 60 * 8;
    /**
     * 颁发者信息
     */
    public static final String ISSUER = "小鸟程序员";
    /**
     * BASE64编码前的密钥信息
     */
    public static final String SECRET_KEY = "BIRD";
    /**
     * 最终密钥信息
     */
    public static final String KEY = Base64.getMimeEncoder().encodeToString(SECRET_KEY.getBytes());


    /**
     * @Author lipu
     * @Date 2020/12/18 20:40
     * @Description 创建jwt令牌
     */
    public static String initJwt(String account) {
        JwtBuilder builder = Jwts.builder()
                //唯一ID
                .setId(UUID.randomUUID().toString())
                //Jwt的认证用户
                .setSubject(account)
                //颁发人
                .setIssuer(ISSUER)
                //颁发时间
                .setIssuedAt(new Date())
                //加密算法和密钥(盐) 设置签证
                .signWith(SignatureAlgorithm.HS256, KEY)
                //设置令牌过期时间 2小时
                .setExpiration(new Date(System.currentTimeMillis() + TIME));
        return builder.compact();
    }

    /**
     * @Author lipu
     * @Date 2020/12/18 22:55
     * @Description 令牌解析
     */
    public static void parseJwt(String jwt) {
        //令牌解析
        Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
    }

}
