package alanisia.blog.service;

import alanisia.blog.common.util.CacheUtil;
import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.dao.AccountDao;
import alanisia.blog.dao.CommentDao;
import alanisia.blog.dto.CommentDTO;
import alanisia.blog.model.Comment;
import alanisia.blog.vo.CommentVO;
import alanisia.blog.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommentService {
  @Autowired private AccountDao accountDao;
  @Autowired private CommentDao commentDao;

  private static final String CACHE_COMMENT = "comments";
  private static final String CACHE_REPLY = "replies";
  private static final long CACHE_EXPIRE = 15 * 60 * 1000;

  public CommentDTO comment(CommentVO commentVO) {
    log.debug("Comment: {}", JsonUtil.json(commentVO));
    Comment comment = new Comment().setBlogId(commentVO.getBlogId()).setLike(0).setCommentId(0)
      .setAccountId(commentVO.getAccountId()).setContent(commentVO.getContent()).setTargetId(0);
    commentDao.insert(comment);
    CommentDTO commentDTO = new CommentDTO().setId(comment.getId()).setAccountId(comment.getAccountId())
      .setBlogId(comment.getBlogId()).setCommentId(0).setTargetId(0).setTarget("").setLike(comment.getLike())
      .setCommenter(accountDao.select(comment.getAccountId()).getUsername()).setContent(comment.getContent())
      .setCreateAt(comment.getCreateAt());

    CacheUtil.put(CacheUtil.generateKey(CACHE_COMMENT, commentDTO.getBlogId() + "::" + commentDTO.getId()),
      commentDTO, CACHE_EXPIRE);
    return commentDTO;
  }

  public CommentDTO reply(ReplyVO replyVO) {
    log.debug("Reply: {}", JsonUtil.json(replyVO));
    Comment reply = new Comment().setAccountId(replyVO.getAccountId()).setLike(0)
      .setBlogId(replyVO.getBlogId()).setCommentId(replyVO.getCommentId())
      .setTargetId(replyVO.getTargetId()).setContent(replyVO.getContent());
    commentDao.insert(reply);
    CommentDTO commentDTO = new CommentDTO().setId(reply.getId()).setAccountId(reply.getAccountId())
      .setBlogId(reply.getBlogId()).setCommentId(reply.getCommentId()).setLike(0)
      .setTargetId(reply.getTargetId()).setContent(reply.getContent())
      .setCommenter(accountDao.select(reply.getCommentId()).getUsername())
      .setTarget(accountDao.select(replyVO.getTargetId()).getUsername())
      .setCreateAt(reply.getCreateAt());
    CacheUtil.put(CacheUtil.generateKey(CACHE_REPLY, commentDTO.getCommentId() + "::" + commentDTO.getId()),
      commentDTO, CACHE_EXPIRE);
    return commentDTO;
  }

  public void removeComment(long commentId) {
    commentDao.replies(commentId).forEach(r -> {
      CacheUtil.remove(CacheUtil.generateKey(CACHE_REPLY, commentId + "::" + r.getId()));
      commentDao.delete(r.getId());
    });
    commentDao.delete(commentId);
    // CacheUtil.remove(CacheUtil.generateKey(CACHE_COMMENT, ));
  }

  public boolean removeReply(long replyId) {
    commentDao.delete(replyId);
    return true;
  }

  public List<Comment> removeAll(long blogId) {
    List<Comment> comments = commentDao.comments(blogId);
    commentDao.deleteByBlogId(blogId);
    return comments;
  }

  public List<CommentDTO> getComments(long blogId) {
    List<CommentDTO> comments = new ArrayList<>();
    commentDao.comments(blogId).forEach(c -> {
      if (0 == c.getCommentId()) {
        CommentDTO comment = new CommentDTO().setAccountId(c.getAccountId())
          .setBlogId(c.getBlogId()).setCommentId(0).setTargetId(0).setTarget("")
          .setContent(c.getContent()).setLike(c.getLike()).setCreateAt(c.getCreateAt())
          .setCommenter(accountDao.select(c.getAccountId()).getUsername());
        comments.add(comment);
      }
    });
    return comments;
  }

  public List<CommentDTO> getReplies(long commentId) {
    List<CommentDTO> replies = new ArrayList<>();
    commentDao.replies(commentId).forEach(c -> {
      CommentDTO reply = new CommentDTO().setAccountId(c.getAccountId())
        .setBlogId(c.getBlogId()).setCommentId(c.getCommentId()).setTargetId(c.getTargetId())
        .setCommenter(accountDao.select(c.getAccountId()).getUsername())
        .setTarget(accountDao.select(c.getTargetId()).getUsername()).setLike(c.getLike())
        .setContent(c.getContent()).setCreateAt(c.getCreateAt());
      replies.add(reply);
    });
    return replies;
  }
}
