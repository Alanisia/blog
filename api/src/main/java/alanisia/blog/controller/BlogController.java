package alanisia.blog.controller;

import alanisia.blog.common.result.R;
import alanisia.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
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
    // log.info("blog: newest");
    return R.ok().setData(blogService.ListOfNewest());
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

  @GetMapping("/blog/{id}")
  public R details(@PathVariable("id") long id) {
    return R.ok().setData(blogService.detail(id));
  }
}
