package alanisia.blog.controller;

import alanisia.blog.common.result.R;
import alanisia.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
  @Autowired private CommentService commentService;

  @GetMapping("/comments")
  public R comments(long blogId) {
    return R.ok();
  }

  @GetMapping("/replies")
  public R replies(long commentId) {
    return R.ok();
  }

  @PostMapping("/comment/create")
  public R comment(long commentId, long targetId, long blogId, long accountId, String content) {
    return R.ok();
  }

  @PostMapping("/comment/delete")
  public R deleteComment() {
    return R.ok();
  }

  @PostMapping("/like")
  public R like(long commentId) {
    return R.ok();
  }
}
