package alanisia.blog.service;

import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.dao.AccountDao;
import alanisia.blog.dao.BlogDao;
import alanisia.blog.dao.CategoryDao;
import alanisia.blog.dto.BlogDetail;
import alanisia.blog.dto.BlogItem;
import alanisia.blog.dto.BlogPagination;
import alanisia.blog.model.Account;
import alanisia.blog.model.Blog;
import alanisia.blog.vo.BlogVO;
import alanisia.blog.vo.AccountIdAndBlogId;
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
      .setDraft(vo.getDraft());
    blogDao.insert(blog);
  }

  public void updateBlog(UpdateBlogVO vo) {
    log.debug("update blog: blog = {}", JsonUtil.json(vo));
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

  public void starBlog(AccountIdAndBlogId accountIdAndBlogId) {
    log.debug("star = {}", JsonUtil.json(accountIdAndBlogId));
    blogDao.star(accountIdAndBlogId.getAccountId(), accountIdAndBlogId.getBlogId());
  }

  public void cancelStar(AccountIdAndBlogId accountIdAndBlogId) {
    log.debug("cancel star = {}", JsonUtil.json(accountIdAndBlogId));
    blogDao.cancelStar(accountIdAndBlogId.getAccountId(), accountIdAndBlogId.getBlogId());
  }

  public void likeBlog(AccountIdAndBlogId accountIdAndBlogId) {
    log.debug("like = {}", JsonUtil.json(accountIdAndBlogId));
    blogDao.like(accountIdAndBlogId.getAccountId(), accountIdAndBlogId.getBlogId());
  }

  public void cancelLike(AccountIdAndBlogId accountIdAndBlogId) {
    log.debug("cancel like = {}", JsonUtil.json(accountIdAndBlogId));
    blogDao.cancelLike(accountIdAndBlogId.getAccountId(), accountIdAndBlogId.getBlogId());
  }

  @Cacheable(value = "newest")
  public List<BlogItem> listOfNewest() {
    log.debug("blog newest");
    List<Blog> newestBlogs = blogDao.newest(50);
    List<BlogItem> items = new ArrayList<>();
    newestBlogs.forEach(blog -> {
      int likes = blogDao.likes(blog.getId());
      int stars = blogDao.stars(blog.getId());
      BlogItem item = new BlogItem().setId(blog.getId())
        .setCategory(categoryDao.category(blog.getCategoryId()).getName())
        .setAuthor(accountDao.select(blog.getAccountId()).getUsername())
        .setTitle(blog.getTitle()).setStar(stars).setComment(0) // TODO: comment
        .setLike(likes).setUpdateAt(blog.getUpdateAt());
      items.add(item);
    });
    return items;
  }

  @Cacheable(value = "list_category", key = "#id + '-' + #page")
  public BlogPagination listWithCategory(int id, int page, int limit) {
    log.debug("list with category: id = {}, page = {}, limit = {}", id, page, limit);
    BlogPagination p = new BlogPagination()
      .setCurrentPage(page).setPages(blogDao.countOfCategory(id) % limit + 1);
    int offset = limit * (page - 1);
    List<Blog> blogs = blogDao.selectByCategory(id, limit, offset);
    List<BlogItem> items = new ArrayList<>();
    blogs.forEach(blog -> {
      int likes = blogDao.likes(blog.getId());
      int stars = blogDao.stars(blog.getId());
      BlogItem item = new BlogItem().setId(blog.getId())
        .setCategory(categoryDao.category(blog.getCategoryId()).getName())
        .setAuthor(accountDao.select(blog.getAccountId()).getUsername())
        .setTitle(blog.getTitle()).setStar(stars).setComment(0) // TODO: comment
        .setLike(likes).setUpdateAt(blog.getUpdateAt());
      items.add(item);
    });
    return p.setItems(items);
  }

  @Cacheable(value = "blog_detail", key = "#id")
  public BlogDetail detail(long id) {
    log.debug("blog detail: id = {}", id);
    Blog blog = blogDao.select(id);
    Account account = accountDao.select(blog.getAccountId());
    int likes = blogDao.likes(blog.getId());
    int stars = blogDao.stars(blog.getId());
    return new BlogDetail().setId(id).setTitle(blog.getTitle()).setStars(stars)
      .setAuthor(account.getUsername()).setUpdateTime(blog.getUpdateAt()).setLikes(likes)
      .setCategory(categoryDao.category(blog.getCategoryId()).getName())
      .setContent(blog.getContent());
  }
}
