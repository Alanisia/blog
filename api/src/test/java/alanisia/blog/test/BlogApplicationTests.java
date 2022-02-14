package alanisia.blog.test;

import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.dto.BlogItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import alanisia.blog.common.util.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Slf4j
@SpringBootTest
class BlogApplicationTests {
  @Autowired private RedisTemplate<String, String> redisTemplate;

	@Test
	void testJWT() {
		String subject = "ABC";
		String jwt = JwtUtil.sign(subject);
		assert JwtUtil.auth(jwt, subject);
	}

	@Test
	void testRedisTemplate() {
	  String key = "newest::alanisia.blog.service.BlogService@27cdbedc-public java.util.List alanisia.blog.service.BlogService.ListOfNewest()-";
    String v = redisTemplate.opsForValue().get(key);
	  log.debug("{}", JsonUtil.json(v));
  }
}
