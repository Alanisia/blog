package alanisia.blog.service;

import alanisia.blog.dao.BlogDao;
import alanisia.blog.dto.BlogDto;
import alanisia.blog.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BlogService {
  @Autowired private BlogDao blogDao;

  public void createBlog() {
    log.debug("create blog: blog = ");
    Blog blog = new Blog();
    blogDao.insert(blog);
  }

  public void deleteBlog(long id) {
    log.debug("delete blog: id = {}", id);
    blogDao.delete(id);
  }

  public void updateBlog(long id) {


  }

  @Cacheable(value = "newest")
  public List<BlogDto> ListOfNewest() {
    // List<BlogDto> blogs = blogDao.newest();
    return null;
  }
}
