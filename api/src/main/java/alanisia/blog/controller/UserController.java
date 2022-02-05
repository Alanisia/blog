package alanisia.blog.controller;

import alanisia.blog.common.result.R;
import alanisia.blog.dto.BlogInfo;
import alanisia.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {
  @Autowired private UserService userService;

  @GetMapping("/info/{id}")
  public R info(@PathVariable("id") long id) {
    return R.ok().setData(userService.detail(id));
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
