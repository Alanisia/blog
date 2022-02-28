package alanisia.blog.service;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.enums.Role;
import alanisia.blog.common.util.CaptchaUtil;
import alanisia.blog.common.util.CryptoUtil;
import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.common.util.JwtUtil;
import alanisia.blog.dao.AccountDao;
import alanisia.blog.vo.Login;
import alanisia.blog.vo.Register;
import alanisia.blog.dto.Token;
import alanisia.blog.exception.BusinessException;
import alanisia.blog.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AccountService {
  @Autowired private AccountDao accountDao;
  @Autowired private RedisTemplate<String, String> redisTemplate;
  public static final String TOKENS = "tokens";

  public Token login(Login login) {
    if (login.getCaptchaCode().equals(redisTemplate.opsForValue().get(login.getCaptchaImage()))) {
      Account account = accountDao.getByEmail(login.getEmail());
      if (account == null)
        throw new BusinessException(Result.ACCOUNT_NOT_FOUND);
      log.debug("account = {}", JsonUtil.json(account));
      String encodedPassword = CryptoUtil.sha256(login.getPassword());
      if (!account.getPassword().equals(encodedPassword))
        throw new BusinessException(Result.PASSWORD_INCORRECT);
      else {
        String token = signToken(account);
        return new Token().setAccountId(account.getId()).setToken(token);
      }
    }
    throw new BusinessException(Result.CAPTCHA_ERROR);
  }

  public Result register(Register register) {
    if (checkCaptcha(register.getCaptchaCode(), register.getCaptchaImage())) {
      if (accountDao.getByEmail(register.getEmail()) != null)
        throw new BusinessException(Result.ACCOUNT_EXISTED);
      Account account = new Account()
        .setEmail(register.getEmail())
        .setUsername(register.getUsername())
        .setRoleId(Role.MEMBER.getId())
        .setPassword(CryptoUtil.sha256(register.getPassword()));
      log.debug("account = {}", JsonUtil.json(account));
      accountDao.insert(account);
      return Result.OK;
    }
    throw new BusinessException(Result.CAPTCHA_ERROR);
  }

  private boolean checkCaptcha(String captcha, String image) {
    return captcha.equals(redisTemplate.opsForValue().get(image));
  }

  public CaptchaUtil.Captcha captcha(String oldImage) {
    if (StringUtils.hasLength(oldImage)) redisTemplate.delete(oldImage);
    CaptchaUtil.Captcha c = CaptchaUtil.generate(200, 120);
    redisTemplate.opsForValue().set(c.getBase64(), c.getCode(), 30, TimeUnit.MINUTES);
    return c;
  }

  public boolean tokenAuthorize(String token) {
    if (token == null) return false;
    String subject = JwtUtil.subject(token);
    String value = redisTemplate.opsForValue().get(subject);
    log.debug("value = {}, token = {}, subject = {}", value, token, subject);
    return token.equals(value);
  }

  public String signToken(Account account) {
    String subject = String.valueOf(account.getId());
    String token = JwtUtil.sign(subject);
    redisTemplate.opsForValue().set(String.valueOf(account.getId()), token, 3, TimeUnit.MINUTES);
    return token;
  }

  public void logout(String accountId) {
    redisTemplate.delete(accountId);
  }
}
