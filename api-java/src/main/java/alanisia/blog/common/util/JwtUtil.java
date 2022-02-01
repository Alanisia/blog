package alanisia.blog.common.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import java.security.Key;

public final class JwtUtil {
  private static final String SECRET = "blog-api";

  public static String sign() {
    // Jwts.builder().signWith()
    return "";
  }

  public static boolean auth(String token) {
    // JwtParser parser = Jwts.parserBuilder().setSigningKey(SECRET).build();
    // Jwt jwt = parser.
    return false;
  }
}

