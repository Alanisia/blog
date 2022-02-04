package alanisia.blog.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import alanisia.blog.common.util.JwtUtil;

@SpringBootTest
class BlogApplicationTests {

	@Test
	void testJWT() {
		String subject = "ABC";
		String jwt = JwtUtil.sign(subject);
		assert JwtUtil.auth(jwt, subject);
	}

}
