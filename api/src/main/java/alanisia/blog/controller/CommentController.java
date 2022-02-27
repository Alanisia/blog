package alanisia.blog.controller;

import alanisia.blog.common.result.R;
import alanisia.blog.service.CommentService;
import alanisia.blog.vo.CommentVO;
import alanisia.blog.vo.ReplyVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CommentController {
  @Autowired private CommentService commentService;

  @GetMapping("/comments")
  public R comments(long blogId) {
    return R.ok().setData(commentService.getComments(blogId));
  }

  @GetMapping("/replies")
  public R replies(long commentId) {
    return R.ok().setData(commentService.getReplies(commentId));
  }

  @PostMapping("/comment")
  public R comment(@RequestBody CommentVO commentVO) {
    commentService.comment(commentVO);
    return R.ok();
  }

  @PostMapping("/reply")
  public R reply(@RequestBody ReplyVO replyVO) {
    commentService.reply(replyVO);
    return R.ok();
  }

  @PostMapping("/comment/deleteAll")
  public R deleteAllComments(long blogId) {
    commentService.removeAll(blogId);
    return R.ok();
  }

  @PostMapping("/comment/delete")
  public R deleteComment(long commentId) {
    commentService.removeComment(commentId);
    return R.ok();
  }

  @PostMapping("/reply/delete")
  public R deleteReply(long replyId) {
    commentService.removeReply(replyId);
    return R.ok();
  }

  // TODO: have not decided if use this method to update likes
  @PostMapping("/comment/like")
  public R like(long commentId) {
    return R.ok();
  }
}
