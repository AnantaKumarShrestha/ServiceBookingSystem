package com.morgan.project1.servicebookingsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    private static final String SECRET = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";


    public String createToken(UserDetails userDetails) {
        return createToken(new HashMap<>(), userDetails);
    }

    public String createToken(Map<String, Object> claims ,UserDetails userDetails) {

        return Jwts.builder().
                setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public String getUsernameFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

     public String refreshToken(String token) {
        return extractClaims(token, Claims::getSubject);
     }

}
