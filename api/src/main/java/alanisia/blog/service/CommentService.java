package alanisia.blog.service;

import alanisia.blog.dao.CommentDao;
import alanisia.blog.dto.CommentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentService {
  @Autowired private CommentDao commentDao;

  @CachePut(value = "comments", key = "#blogId")
  public void comment(long blogId) {

  }

  @CachePut(value = "replies", key = "#commentId")
  public void reply(long commentId) {

  }

  @CacheEvict(value = "replies", key = "#commentId")
  public void remove(long commentId) {

  }

  @CacheEvict(value = "comments", key = "#blogId")
  public void removeAll(long blogId) {

  }

  @Cacheable(value = "comments", key = "#blogId")
  public List<CommentDTO> getComments(long blogId) {
    return null;
  }

  @Cacheable(value = "replies", key = "#commentId")
  public List<CommentDTO> getReplies(long commentId) {
    return null;
  }
}
