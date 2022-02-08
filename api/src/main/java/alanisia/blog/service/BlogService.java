package alanisia.blog.service;

import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.dao.AccountDao;
import alanisia.blog.dao.BlogDao;
import alanisia.blog.dao.CategoryDao;
import alanisia.blog.dto.BlogDetail;
import alanisia.blog.dto.BlogItem;
import alanisia.blog.model.Account;
import alanisia.blog.model.Blog;
import alanisia.blog.vo.BlogVO;
import alanisia.blog.vo.StarVO;
import alanisia.blog.vo.UpdateBlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BlogService {
  @Autowired private AccountDao accountDao;
  @Autowired private CategoryDao categoryDao;
  @Autowired private BlogDao blogDao;

  public void insertBlog(BlogVO vo) {
    log.debug("create blog: blog = {}", JsonUtil.json(vo));
    Blog blog = new Blog()
      .setAccountId(vo.getAuthorId())
      .setCategoryId(vo.getCategory().getId())
      .setTitle(vo.getTitle())
      .setContent(vo.getContent())
      .setDraft(vo.getDraft())
      .setLike(0).setStar(0);
    blogDao.insert(blog);
  }

  public void updateBlog(UpdateBlogVO vo) {
    Blog blog = new Blog()
      .setAccountId(vo.getBlog().getAuthorId())
      .setCategoryId(vo.getBlog().getCategory().getId())
      .setTitle(vo.getBlog().getTitle())
      .setContent(vo.getBlog().getContent())
      .setDraft(vo.getBlog().getDraft());
    blogDao.update(vo.getId(), blog);
  }

  public void deleteBlog(long id) {
    log.debug("delete blog: id = {}", id);
    blogDao.delete(id);
  }

  public void starBlog(StarVO starVO) {
    log.debug("star = {}", JsonUtil.json(starVO));
    blogDao.star(starVO.getAccountId(), starVO.getBlogId());
  }

  public void cancelStar(StarVO starVO) {
    log.debug("star = {}", JsonUtil.json(starVO));
    blogDao.cancelStar(starVO.getAccountId(), starVO.getBlogId());
  }

  @Cacheable(value = "newest")
  public List<BlogItem> ListOfNewest() {
    List<Blog> newestBlogs = blogDao.newest(50);
    List<BlogItem> items = new ArrayList<>();
    newestBlogs.forEach(blog -> {
      BlogItem item = new BlogItem().setId(blog.getId())
        .setAuthor(accountDao.select(blog.getAccountId()).getUsername())
        .setTitle(blog.getTitle()).setStar(blog.getStar()).setComment(0) // TODO: comment
        .setLike(blog.getLike()).setUpdateAt(blog.getUpdateAt());
      items.add(item);
    });
    return items;
  }

  @Cacheable(value = "blog_detail", key = "#id")
  public BlogDetail detail(long id) {
    Blog blog = blogDao.select(id);
    Account account = accountDao.select(blog.getAccountId());
    return (BlogDetail) new BlogDetail()
      .setAuthor(account.getUsername())
      .setCategory(categoryDao.category(blog.getCategoryId()).getName())
      .setContent(blog.getContent());
  }
}
