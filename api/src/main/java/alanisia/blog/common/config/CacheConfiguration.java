package alanisia.blog.common.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Objects;

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
  public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
    RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration
      .defaultCacheConfig().entryTtl(Duration.ofMinutes(1)).disableCachingNullValues();
    return RedisCacheManager.builder(Objects.requireNonNull(redisTemplate.getConnectionFactory()))
      .cacheDefaults(cacheConfiguration).build();
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
