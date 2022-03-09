package alanisia.blog.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public final class JwtUtil {
  private static final String SECRET = "blog-api";
  private static final SecretKey KEY = generateKey();

  private static SecretKey generateKey() {
    String sha256 = CryptoUtil.sha256(SECRET);
    String encodedKey = Encoders.BASE64.encode(sha256.getBytes());
    return Keys.hmacShaKeyFor(encodedKey.getBytes());
  }

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
