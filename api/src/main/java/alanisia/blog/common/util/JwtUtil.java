package alanisia.blog.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public final class JwtUtil {
  // private static final String SECRET = "blog-api";
  private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public static String sign(String subject) {
    return Jwts.builder().setSubject(subject).signWith(KEY).compact();
  }

  public static boolean auth(String token, String subject) {
    return subject.equals(subject(token));
  }

  public static String subject(String token) {
    return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody().getSubject();
  }
}
