package alanisia.blog.controller;

import alanisia.blog.common.result.R;
import alanisia.blog.dto.BlogInfo;
import alanisia.blog.service.UserService;
import alanisia.blog.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {
  @Autowired private UserService userService;

  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") long id) {
    return R.ok().setData(userService.detail(id));
  }

  @PostMapping("/info/modify/{id}")
  public R modifyInfo(@PathVariable("id") long id, UserDetailVO detail) {
    userService.update(id, detail);
    return R.ok();
  }

  @GetMapping("/history/{id}")
  public R history(@PathVariable("id") long id) {
    return R.ok().setData(new BlogInfo()
        .setCount(userService.histories(id))
        .setItems(userService.history(id)));
  }

  @GetMapping("/star/{id}")
  public R star(@PathVariable("id") long id) {
    return R.ok().setData(new BlogInfo()
      .setCount(userService.stars(id))
      .setItems(userService.star(id)));
  }

  @GetMapping("/publish/{id}")
  public R publish(@PathVariable("id") long id) {
    return R.ok().setData(new BlogInfo()
      .setCount(userService.publishes(id))
      .setItems(userService.publish(id)));
  }

  @GetMapping("/draft/{id}")
  public R draft(@PathVariable("id") long id) {
    return R.ok().setData(new BlogInfo()
      .setCount(userService.drafts(id))
      .setItems(userService.draft(id)));
  }
}
