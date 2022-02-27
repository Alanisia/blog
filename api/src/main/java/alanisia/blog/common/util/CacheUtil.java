package alanisia.blog.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public final class CacheUtil {
  @Autowired private static RedisTemplate<String, Object> redisTemplate;

  public static String generateKey(String name, String key) {
    return name + ";;" + key;
  }

  public static void put(String key, Object object) {
    redisTemplate.opsForValue().set(key, object);
  }

  public static void put(String key, Object object, long expire) {
    redisTemplate.opsForValue().set(key, object, expire, TimeUnit.MILLISECONDS);
  }

  public static Object get(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public static void remove(String key) {
    redisTemplate.delete(key);
  }
}
