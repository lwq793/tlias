package org.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT令牌操作工具类
 * 提供生成和解析JWT令牌的功能
 */
public class JwtUtils {
    
    // JWT密钥（与测试类保持一致）
    private static final String SECRET_KEY = "bHdxMTIz";
    
    // 令牌过期时间：12小时（单位：毫秒）
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000;
    
    /**
     * 生成JWT令牌
     * @param claims 需要存储在令牌中的自定义数据
     * @return 生成的JWT令牌字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 指定加密算法和密钥
                .addClaims(claims) // 添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置令牌过期时间（12小时）
                .compact(); // 生成令牌
        return jwt;
    }
    
    /**
     * 解析JWT令牌
     * @param token JWT令牌字符串
     * @return 解析后得到的自定义数据
     * @throws io.jsonwebtoken.JwtException 如果令牌无效、过期或签名验证失败
     */
    public static Claims parseJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY) // 指定密钥
                .parseClaimsJws(token) // 解析令牌
                .getBody(); // 获取自定义数据
        return claims;
    }
}