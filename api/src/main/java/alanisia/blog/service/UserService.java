package alanisia.blog.service;

import alanisia.blog.common.enums.Result;
import alanisia.blog.dao.AccountDao;
import alanisia.blog.dao.BlogDao;
import alanisia.blog.dao.CategoryDao;
import alanisia.blog.dao.UserDao;
import alanisia.blog.dto.BlogItem;
import alanisia.blog.dto.UserDetailDTO;
import alanisia.blog.exception.BusinessException;
import alanisia.blog.model.*;
import alanisia.blog.vo.UserDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class UserService {
  @Autowired private AccountDao accountDao;
  @Autowired private BlogDao blogDao;
  @Autowired private CategoryDao categoryDao;
  @Autowired private UserDao userDao;

  @CachePut(value = "user_detail", key = "#id")
  public void update(long id, UserDetailVO detailVO) {
    Account account = accountDao.select(id);
    if (account == null)
      throw new BusinessException(Result.ACCOUNT_NOT_FOUND);
    else {
      UserDetail detail = userDao.detail(id);
      if (detail == null) {
        detail = new UserDetail()
          .setAccountId(id)
          .setGender(detailVO.getGender())
          .setAvatar(detailVO.getAvatar());
        userDao.insert(detail);
      } else userDao.update(detail);
      if (!account.getUsername().equals(detailVO.getUsername())) {
        account.setUsername(detailVO.getUsername());
        accountDao.update(id, account);
      }
    }
  }

  public void removeHistory(long id, long blogId) {
    userDao.removeHistory(id, blogId);
  }

  @Cacheable(value = "user_detail", key = "#id")
  public UserDetailDTO detail(long id) {
    UserDetail detail = userDao.detail(id);
    Account account = accountDao.select(id);
    UserDetailDTO detailDTO = new UserDetailDTO()
      .setId(id)
      .setEmail(account.getEmail())
      .setUsername(account.getUsername())
      .setPublish(userDao.publishCount(id))
      .setStar(userDao.starCount(id));
    if (detail != null)
      detailDTO.setGender(detailDTO.getGender())
        .setAvatar(detailDTO.getAvatar());
    return detailDTO;
  }

  @Cacheable(value = "user_history", key = "#id")
  public List<BlogItem> history(long id) {
    List<BlogHistory> histories = userDao.history(id);
    List<BlogItem> items = new ArrayList<>();
    histories.forEach(b -> {
      long blogId = b.getBlogId();
      long accountId = b.getAccountId();
      Blog blog = blogDao.select(blogId);
      Category category = categoryDao.category(blog.getCategoryId());
      Account account = accountDao.select(accountId);
      BlogItem item = new BlogItem()
        .setId(blog.getId())
        .setAuthor(account.getUsername())
        .setCategory(category.getName())
        .setTitle(blog.getTitle())
        .setLike(blog.getLike())
        .setStar(blog.getStar())
        .setUpdateAt(blog.getUpdateAt());
      items.add(item);
    });
    return items;
  }

  @Cacheable(value = "user_star", key = "#id")
  public List<BlogItem> star(long id) {
    List<StarBlog> starBlogs = userDao.star(id);
    List<BlogItem> items = new ArrayList<>();
    starBlogs.forEach(b -> {
      long blogId = b.getBlogId();
      long accountId = b.getAccountId();
      Blog blog = blogDao.select(blogId);
      Category category = categoryDao.category(blog.getCategoryId());
      Account account = accountDao.select(accountId);
      BlogItem item = new BlogItem()
        .setId(blog.getId())
        .setAuthor(account.getUsername())
        .setCategory(category.getName())
        .setTitle(blog.getTitle())
        .setLike(blog.getLike())
        .setStar(blog.getStar())
        .setUpdateAt(blog.getUpdateAt());
      items.add(item);
    });
    return items;
  }

  @Cacheable(value = "user_publish", key = "#id")
  public List<BlogItem> publish(long id) {
    List<Blog> blogs = userDao.publish(id);
    List<BlogItem> items = new ArrayList<>();
    blogs.forEach(b -> {
      long blogId = b.getId();
      long accountId = b.getAccountId();
      Blog blog = blogDao.select(blogId);
      Category category = categoryDao.category(blog.getCategoryId());
      Account account = accountDao.select(accountId);
      BlogItem item = new BlogItem()
        .setId(blog.getId())
        .setAuthor(account.getUsername())
        .setCategory(category.getName())
        .setTitle(blog.getTitle())
        .setLike(blog.getLike())
        .setStar(blog.getStar())
        .setUpdateAt(blog.getUpdateAt());
      items.add(item);
    });
    return items;
  }

  @Cacheable(value = "user_draft", key = "#id")
  public List<BlogItem> draft(long id) {
    List<Blog> blogs = userDao.draft(id);
    List<BlogItem> items = new ArrayList<>();
    blogs.forEach(b -> {
      long blogId = b.getId();
      long accountId = b.getAccountId();
      Blog blog = blogDao.select(blogId);
      Category category = categoryDao.category(blog.getCategoryId());
      Account account = accountDao.select(accountId);
      BlogItem item = new BlogItem()
        .setId(blog.getId())
        .setAuthor(account.getUsername())
        .setCategory(category.getName())
        .setTitle(blog.getTitle())
        .setLike(blog.getLike())
        .setStar(blog.getStar())
        .setUpdateAt(blog.getUpdateAt());
      items.add(item);
    });
    return items;
  }

  @Cacheable(value = "user_draft_count", key = "#id")
  public int drafts(long id) {
    return userDao.draftCount(id);
  }

  @Cacheable(value = "user_history_count", key = "#id")
  public int histories(long id) {
    return userDao.historyCount(id);
  }

  @Cacheable(value = "user_publish_count", key = "#id")
  public int publishes(long id) {
    return userDao.publishCount(id);
  }

  @Cacheable(value = "user_star_count", key = "#id")
  public int stars(long id) {
    return userDao.starCount(id);
  }
}
