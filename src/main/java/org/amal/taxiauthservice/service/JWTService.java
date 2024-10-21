package org.amal.taxiauthservice.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService implements CommandLineRunner {

  @Value("${jwt.expiry}")
  private int expiry;

  @Value("${jwt.secret}")
  private String SECRET;

  public String createToken(Map<String, Object> payload, String email){
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expiry*1000L);
    return Jwts.builder()
              .setClaims(payload)
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(expiryDate)
              .setSubject(email)
              .signWith(getSignInKey(), SignatureAlgorithm.HS256)
              .compact();
  }

  public String createToken(String email){
    return createToken(new HashMap<>(), email);
  }


  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public Claims extractAllPayloads(String token) {
    return Jwts
          .parserBuilder()
          .setSigningKey(getSignInKey())
          .build()
          .parseClaimsJws(token)
          .getBody();
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllPayloads(token);
    return claimsResolver.apply(claims);
  }


  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String extractEmail(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Object extractPayload(String token, String payloadKey) {
    Claims claim = extractAllPayloads(token);
    return claim.get(payloadKey);
  }


  public boolean validateToken(String token, String email) {
    final String userEmailFetchedFromToken = extractEmail(token);
    return (userEmailFetchedFromToken.equals(email)) && !isTokenExpired(token);
  }

  @Override
  public void run(String... args) throws Exception {
    Map<String, Object> mp = new HashMap<>();
    mp.put("email", "a@b.com");
    mp.put("phoneNumber", "9999999999");
    createToken(mp, "a@b.com");
  }


}
