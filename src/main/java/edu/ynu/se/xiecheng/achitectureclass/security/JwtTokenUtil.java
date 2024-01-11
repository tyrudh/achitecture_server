package edu.ynu.se.xiecheng.achitectureclass.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
@Component
public class JwtTokenUtil {
    private String secret = "12345678901234567890123456789012"; // 你需要设置一个密钥，长度至少为32个字符
    private Key key = Keys.hmacShaKeyFor(secret.getBytes());
    // 生成令牌
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 设置过期时间
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    // 验证令牌
    public Boolean validateToken(String token, String username) {
        final String usernameInToken = getUsernameFromToken(token);
        return (usernameInToken.equals(username) && !isTokenExpired(token));
    }
    // 从令牌获取用户名
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    // 检查令牌是否过期
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
