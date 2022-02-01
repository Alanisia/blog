package alanisia.blog.common.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class CacheConfig extends CachingConfigurerSupport {
  @Bean
  @Override
  public KeyGenerator keyGenerator() {
    return (target, method, params) -> {
      StringBuilder builder = new StringBuilder();
      builder.append(target).append('-').append(method).append('-');
      for (Object param : params) builder.append(param).append(',');
      return builder.toString();
    };
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate() {
    StringRedisTemplate redisTemplate = new StringRedisTemplate();
    // redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer());
    return redisTemplate;
  }
}
