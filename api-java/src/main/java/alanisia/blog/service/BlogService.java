package alanisia.blog.service;

import alanisia.blog.dao.BlogDao;
import alanisia.blog.dto.BlogDto;
import alanisia.blog.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BlogService {
  @Autowired private BlogDao blogDao;

  public void createBlog() {
    Blog blog = new Blog();
    blogDao.insert(blog);
  }

  public void deleteBlog(long id) {
    blogDao.delete(id);
  }

  public void updateBlog(long id) {

  }

  public List<BlogDto> ListOfNewest() {
    // List<BlogDto> blogs = blogDao.newest();
  }
}
