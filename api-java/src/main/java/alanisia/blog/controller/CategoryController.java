package alanisia.blog.controller;

import alanisia.blog.common.result.R;
import alanisia.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
  @Autowired private CategoryService categoryService;

  @GetMapping("/categories")
  public R categories() {
    return R.ok();
  }

  @GetMapping("/tags")
  public R tags() {
    return R.ok();
  }
}
