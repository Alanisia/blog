package alanisia.blog.common.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@EnableCaching
@Configuration
public class CacheConfiguration extends CachingConfigurerSupport {
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
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
    StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
    // redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer());
    return redisTemplate;
  }
}
