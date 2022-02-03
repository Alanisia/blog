package alanisia.blog.service;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.enums.Role;
import alanisia.blog.common.util.CaptchaUtil;
import alanisia.blog.common.util.CryptoUtil;
import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.common.util.JwtUtil;
import alanisia.blog.dao.AccountDao;
import alanisia.blog.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
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
  public static final String TOKENS = "tokens";

  public Result login(String email, String password, String captcha, String image) {
    if (captcha.equals(redisTemplate.opsForValue().get(image))) {
      Account account = accountDao.getByEmail(email);
      if (account == null) return Result.ACCOUNT_NOT_FOUND;
      try {
        log.debug("account = {}", JsonUtil.json(account));
      } catch (JsonProcessingException e) {
        log.debug("WARN: {}", e.getMessage());
      }
      String encodedPassword = CryptoUtil.sha256(password);
      if (!password.equals(encodedPassword)) return Result.PASSWORD_INCORRECT;
      else {
        signToken(account);
        return Result.OK;
      }
    }
    return Result.CAPTCHA_ERROR;
  }

  public Result register(String email, String username, String password, String captcha, String image) {
    if (checkCaptcha(captcha, image)) {
      if (accountDao.getByEmail(email) != null) return Result.ACCOUNT_EXISTED;
      Account account = new Account().setEmail(email).setUsername(username)
        .setRoleId(Role.MEMBER.getId()).setPassword(CryptoUtil.sha256(password));
      try {
        log.debug("account = {}", JsonUtil.json(account));
      } catch (JsonProcessingException e) {
        log.debug("WARN: {}", e.getMessage());
      }
      accountDao.insert(account);
      return Result.OK;
    }
    return Result.CAPTCHA_ERROR;
  }

  private boolean checkCaptcha(String captcha, String image) {
    return captcha.equals(redisTemplate.opsForValue().get(image));
  }

  public CaptchaUtil.Captcha captcha() {
    CaptchaUtil.Captcha c = CaptchaUtil.generate(200, 120);
    redisTemplate.opsForValue().set(c.getBase64(), c.getCode(), 60, TimeUnit.SECONDS);
    return c;
  }

  public boolean tokenAuthorize(String token) {
    if (token == null) return false;
    String subject = JwtUtil.subject(token);
    String value = redisTemplate.opsForValue().get(subject);
    boolean equal = token.equals(value);
    log.debug("value == token? {}, subject = {}", equal, subject);
    return equal;
  }

  public void signToken(Account account) {
    String subject = account.getEmail() + account.getPassword();
    String token = JwtUtil.sign(subject);
    redisTemplate.opsForValue().set(account.getEmail(), token, 30, TimeUnit.MINUTES);
  }
}
