package alanisia.blog.controller;

import alanisia.blog.common.result.R;
import alanisia.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
public class CategoryController {
  @Autowired private CategoryService categoryService;

  @GetMapping("/categories")
  public R categories() {
//    log.info("categories");
    return R.ok().setData(categoryService.categories());
  }

  @GetMapping("/tags")
  public R tags() {
//    log.info("tags");
    return R.ok().setData(categoryService.tags());
  }
}
