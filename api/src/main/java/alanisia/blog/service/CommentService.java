package alanisia.blog.service;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.dao.AccountDao;
import alanisia.blog.dao.BlogDao;
import alanisia.blog.dao.CommentDao;
import alanisia.blog.dto.CommentDTO;
import alanisia.blog.dto.CommentLiked;
import alanisia.blog.exception.BusinessException;
import alanisia.blog.model.Comment;
import alanisia.blog.vo.CommentLikeVO;
import alanisia.blog.vo.CommentVO;
import alanisia.blog.vo.DeleteCommentVO;
import alanisia.blog.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommentService {
  @Autowired private AccountDao accountDao;
  @Autowired private BlogDao blogDao;
  @Autowired private CommentDao commentDao;

  @CacheEvict(cacheNames = "comments", key = "#commentVO.blogId", beforeInvocation = true)
  public void comment(CommentVO commentVO) {
    log.debug("Comment: {}", JsonUtil.json(commentVO));
    if (commentVO.getContent().length() == 0) throw new BusinessException(Result.NULL_EXCEPTION);
    if (blogDao.select(commentVO.getBlogId()) == null) throw new BusinessException(Result.NULL_EXCEPTION);
    Comment comment = new Comment().setBlogId(commentVO.getBlogId()).setCommentId(0)
      .setAccountId(commentVO.getAccountId()).setContent(commentVO.getContent()).setTargetId(0);
    commentDao.insert(comment);
  }

  @CacheEvict(cacheNames = "replies", key = "#replyVO.commentId", beforeInvocation = true)
  public void reply(ReplyVO replyVO) {
    log.debug("Reply: {}", JsonUtil.json(replyVO));
    if (replyVO.getContent().length() == 0) throw new BusinessException(Result.NULL_EXCEPTION);
    if (commentDao.select(replyVO.getCommentId()) == null) throw new BusinessException(Result.NULL_EXCEPTION);
    Comment reply = new Comment().setAccountId(replyVO.getAccountId())
      .setBlogId(replyVO.getBlogId()).setCommentId(replyVO.getCommentId())
      .setTargetId(replyVO.getTargetId()).setContent(replyVO.getContent());
    commentDao.insert(reply);
  }

  @Caching(evict = {
    @CacheEvict(cacheNames = "replies", key = "#deleteCommentVO.commentId", beforeInvocation = true),
    @CacheEvict(cacheNames = "comments", key = "#deleteCommentVO.blogId", beforeInvocation = true)
  })
  public void removeComment(DeleteCommentVO deleteCommentVO) {
    commentDao.replies(deleteCommentVO.getCommentId()).forEach(r -> traverseRemove(r.getId()));
    commentDao.delete(deleteCommentVO.getCommentId());
    commentDao.removeLikes(deleteCommentVO.getCommentId());
  }

  @CacheEvict(cacheNames = "replies", key = "#deleteCommentVO.commentId", beforeInvocation = true)
  public void removeReply(DeleteCommentVO deleteCommentVO) {
    traverseRemove(deleteCommentVO.getReplyId());
  }

  private void traverseRemove(long replyId) {
    commentDao.delete(replyId);
    commentDao.removeLikes(replyId);
    List<Comment> replies = commentDao.replies(replyId);
    replies.forEach(r -> traverseRemove(r.getId()));
  }

  @CacheEvict(cacheNames = "comments", key = "#deleteCommentVO.blogId", beforeInvocation = true)
  public void removeAll(DeleteCommentVO deleteCommentVO) {
    commentDao.deleteByBlogId(deleteCommentVO.getBlogId());
  }

  @Cacheable(cacheNames = "comments", key = "#blogId")
  public List<CommentDTO> getComments(long blogId) {
    List<CommentDTO> comments = new ArrayList<>();
    commentDao.comments(blogId).forEach(c -> {
      if (0 == c.getCommentId()) {
        CommentDTO comment = new CommentDTO().setId(c.getId()).setAccountId(c.getAccountId())
          .setBlogId(c.getBlogId()).setCommentId(0).setTargetId(0).setTarget("").setContent(c.getContent())
          .setCreateAt(c.getCreateAt()).setCommenter(accountDao.select(c.getAccountId()).getUsername())
          .setLike(commentDao.likes(c.getId())).setTargetContent("");
        comments.add(comment);
      }
    });
    return comments;
  }

  @Cacheable(cacheNames = "replies", key = "#commentId")
  public List<CommentDTO> getReplies(long commentId) {
    log.debug("get replies: commentId = {}", commentId);
    List<CommentDTO> replies = new ArrayList<>();
    traverse(commentId, replies);
    return replies;
  }

  private void traverse(long commentId, List<CommentDTO> replies) {
    commentDao.replies(commentId).forEach(c -> {
      long targetCommentId = c.getCommentId();
      String targetContent = commentDao.select(targetCommentId).getContent();
      CommentDTO reply = new CommentDTO().setAccountId(c.getAccountId()).setBlogId(c.getBlogId()).setId(c.getId())
        .setCommentId(c.getCommentId()).setCommenter(accountDao.select(c.getAccountId()).getUsername())
        .setTargetId(c.getTargetId()).setTarget(accountDao.select(c.getTargetId()).getUsername())
        .setContent(c.getContent()).setCreateAt(c.getCreateAt()).setLike(commentDao.likes(c.getId()))
        .setTargetContent(targetContent);
      replies.add(reply);
      traverse(reply.getId(), replies);
    });
  }

  public CommentLiked liked(long accountId, long commentId) {
    return new CommentLiked().setLiked(commentDao.liked(accountId, commentId) > 0);
  }

  public void like(CommentLikeVO commentLikeVO) {
    commentDao.like(commentLikeVO.getAccountId(), commentLikeVO.getCommentId());
  }

  public void cancelLike(CommentLikeVO commentLikeVO) {
    commentDao.cancelLike(commentLikeVO.getAccountId(), commentLikeVO.getCommentId());
  }
}
