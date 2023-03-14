package uz.fidobiznes.budgetauthminfin.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JWTProvider {
    long expireTime = 1000 * 60 * 60;
    Date expireDate = new Date(System.currentTimeMillis() + expireTime);
//    SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode("newworldorder"));
    String secretKey = "something";

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expireDate).signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
    }
    public Mono<String> generateTokenReactive(String username) {
        return Mono.just(Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expireDate).signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact());
    }


    public String getEmailFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Can't get user!");
        }
    }
    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey (getSigningKey())
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean validate(String authToken){
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            return false;
        }
    }

}
