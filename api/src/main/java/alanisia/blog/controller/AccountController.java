package alanisia.blog.controller;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.result.R;
import alanisia.blog.dto.LoginDto;
import alanisia.blog.dto.LoginResult;
import alanisia.blog.dto.RegisterDto;
import alanisia.blog.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
public class AccountController {
  @Autowired
  private AccountService accountService;

  @PostMapping("/login")
  public R login(@RequestBody LoginDto login) {
    log.info("{}: login", login.getEmail());
    LoginResult result = accountService.login(login);
    return new R(result.getResult()).setData(result.getDto());
  }

  @PostMapping("/register")
  public R register(@RequestBody RegisterDto register) {
    log.info("{}: register", register.getEmail());
    return new R(accountService.register(register));
  }

  @PostMapping("/captcha")
  public R captcha(String captchaImage) {
    log.info("captcha");
    return new R(Result.OK).setData(accountService.captcha(captchaImage).getBase64());
  }

  @PostMapping("/logout")
  public R logout(long id) {
    accountService.logout(String.valueOf(id));
    return R.ok();
  }
}