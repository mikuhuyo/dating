package com.dating.commons.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public class JwtUtils {

    // TOKEN的有效期
    private static final long TOKEN_TIME_OUT = 3600L * 24 * 7;

    // 加密KEY
    private static final String TOKEN_SECRET = "dating";

    /**
     * 生成Token
     *
     * @param params
     * @return
     */
    public static String getToken(Map<String, Object> params) {
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                // 加密方式
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                // 过期时间戳
                .setExpiration(new Date(currentTime + TOKEN_TIME_OUT))
                .addClaims(params)
                .compact();
    }

    /**
     * 获取Token中的claims信息
     */
    public static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token).getBody();
    }


    /**
     * 是否有效 true-有效, false-失效
     */
    public static boolean verifyToken(String token) {

        if (StringUtils.isEmpty(token)) {
            return false;
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}