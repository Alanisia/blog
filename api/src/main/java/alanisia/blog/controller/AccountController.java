package alanisia.blog.controller;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.result.R;
import alanisia.blog.dto.Token;
import alanisia.blog.service.AccountService;
import alanisia.blog.vo.Login;
import alanisia.blog.vo.Logout;
import alanisia.blog.vo.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AccountController {
  @Autowired
  private AccountService accountService;

  @PostMapping("/login")
  public R login(@RequestBody Login login) {
    Token token = accountService.login(login);
    return R.ok().setData(token);
  }

  @PostMapping("/register")
  public R register(@RequestBody Register register) {
    return new R(accountService.register(register));
  }

  @PostMapping("/captcha")
  public R captcha(String captchaImage) {
    return new R(Result.OK).setData(accountService.captcha(captchaImage).getBase64());
  }

  @PostMapping("/logout")
  public R logout(@RequestBody Logout logout) {
    accountService.logout(String.valueOf(logout.getId()));
    return R.ok();
  }
}
