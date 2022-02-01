package alanisia.blog.controller;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.result.R;
import alanisia.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
  @Autowired private AccountService accountService;

  @PostMapping("/login")
  public R login(String email, String password, String captchaCode, String captchaImage) {
    return R.ok();
  }

  @PostMapping("/register")
  public R register(String email, String username, String password,
                    String captchaCode, String captchaImage) {
    return new R(accountService.register(email, username, password, captchaCode, captchaImage));
  }

  @GetMapping("/captcha")
  public R captcha() {
    return new R(Result.OK).setData(accountService.captcha());
  }

  @PostMapping("/logout")
  public R logout() {
    return R.ok();
  }
}
