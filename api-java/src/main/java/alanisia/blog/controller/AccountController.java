package alanisia.blog.controller;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.result.R;
import alanisia.blog.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
public class AccountController {
  @Autowired private AccountService accountService;

  @PostMapping("/login")
  public R login(String email, String password, String captchaCode, String captchaImage) {
    log.info("{}: login", email);
    return new R(accountService.login(email, password, captchaCode, captchaImage));
  }

  @PostMapping("/register")
  public R register(String email, String username, String password,
                    String captchaCode, String captchaImage) {
    log.info("{}: register", email);
    return new R(accountService.register(email, username, password, captchaCode, captchaImage));
  }

  @GetMapping("/captcha")
  public R captcha() {
    log.info("captcha");
    return new R(Result.OK).setData(accountService.captcha().getBase64());
  }

  @PostMapping("/logout")
  public R logout() {
    return R.ok();
  }
}
