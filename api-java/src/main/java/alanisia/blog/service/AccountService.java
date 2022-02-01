package alanisia.blog.service;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.util.CaptchaUtil;
import alanisia.blog.dao.AccountDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AccountService {
  @Autowired private AccountDao accountDao;
  @Autowired private RedisTemplate<String, String> redisTemplate;

  public void login(String email, String password, String captcha, String image) {



  }

  public Result register(String email, String username, String password, String captcha, String image) {
    if (checkCaptcha(captcha, image)) {

      return Result.OK;
    }
    return Result.CAPTCHA_ERROR;
  }

  private boolean checkCaptcha(String captcha, String image) {
    return captcha.equals(redisTemplate.opsForValue().get(image));
  }

  public CaptchaUtil.Captcha captcha() {
    CaptchaUtil.Captcha c = CaptchaUtil.generate(300, 180);
    redisTemplate.opsForValue().set(c.getBase64(), c.getCode(), 60, TimeUnit.SECONDS);
    return c;
  }

  public void tokenAuthorize(String token) {

  }

  public void signToken() {

  }
}