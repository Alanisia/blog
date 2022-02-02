package alanisia.blog.controller;

import alanisia.blog.common.result.R;
import alanisia.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {
  @Autowired private BlogService blogService;

  @PostMapping("/save")
  public R save(String title, String category, String content) {
    return R.ok();
  }

  @PostMapping("/publish")
  public R publish(String title, String category, String content) {
    return R.ok();
  }

  @GetMapping("/newest")
  public R newest() {
    return R.ok();
  }

  @GetMapping("/search")
  public R search(String keyword) {
    return R.ok();
  }

  @GetMapping("/category")
  public R showByCategory(int categoryId) {
    return R.ok();
  }

  @GetMapping("/tag")
  public R showByTag(int tagId) {
    return R.ok();
  }
}
