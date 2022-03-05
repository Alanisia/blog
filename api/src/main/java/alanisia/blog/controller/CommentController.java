package alanisia.blog.controller;

import alanisia.blog.common.result.R;
import alanisia.blog.service.CommentService;
import alanisia.blog.vo.CommentLikeVO;
import alanisia.blog.vo.CommentVO;
import alanisia.blog.vo.DeleteCommentVO;
import alanisia.blog.vo.ReplyVO;
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
  public R deleteAllComments(@RequestBody DeleteCommentVO deleteCommentVO) {
    commentService.removeAll(deleteCommentVO);
    return R.ok();
  }

  @PostMapping("/comment/delete")
  public R deleteComment(@RequestBody DeleteCommentVO deleteCommentVO) {
    commentService.removeComment(deleteCommentVO);
    return R.ok();
  }

  @PostMapping("/reply/delete")
  public R deleteReply(@RequestBody DeleteCommentVO deleteCommentVO) {
    commentService.removeReply(deleteCommentVO);
    return R.ok();
  }

  @PostMapping("/comment/like")
  public R like(@RequestBody CommentLikeVO commentLikeVO) {
    commentService.like(commentLikeVO);
    return R.ok();
  }

  @PostMapping("/comment/like/cancel")
  public R cancelLike(@RequestBody CommentLikeVO commentLikeVO) {
    commentService.cancelLike(commentLikeVO);
    return R.ok();
  }

  @GetMapping("/comment/liked")
  public R liked(long accountId, long commentId) {
    return R.ok().setData(commentService.liked(accountId, commentId));
  }
}
