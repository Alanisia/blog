package alanisia.blog.controller;

import alanisia.blog.common.result.R;
import alanisia.blog.service.BlogService;
import alanisia.blog.vo.BlogVO;
import alanisia.blog.vo.UpdateBlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogController {
  @Autowired private BlogService blogService;

  @PostMapping("/save")
  public R save(@RequestBody BlogVO blog) {
    blogService.insertBlog(blog);
    return R.ok();
  }

  @PostMapping("/publish")
  public R publish(@RequestBody BlogVO blog) {
    blogService.insertBlog(blog);
    return R.ok();
  }

  @PostMapping("/update/save")
  public R updateAndSave(@RequestBody UpdateBlogVO blog) {
    blogService.updateBlog(blog);
    return R.ok();
  }

  @PostMapping("/update/publish")
  public R updateAndPublish(@RequestBody UpdateBlogVO blog) {
    blogService.updateBlog(blog);
    return R.ok();
  }

  @GetMapping("/newest")
  public R newest() {
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

  @GetMapping("/blog/{id}")
  public R details(@PathVariable("id") long id) {
    return R.ok().setData(blogService.detail(id));
  }
}
