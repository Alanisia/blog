package alanisia.blog.service;

import alanisia.blog.common.enums.Result;
import alanisia.blog.dao.AccountDao;
import alanisia.blog.dao.BlogDao;
import alanisia.blog.dao.UserDao;
import alanisia.blog.dto.BlogItem;
import alanisia.blog.dto.UserDetailDTO;
import alanisia.blog.exception.BusinessException;
import alanisia.blog.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired private UserDao userDao;

  @Cacheable(value = "user_detail", key = "user_#id")
  public UserDetailDTO detail(long id) {
    UserDetail detail = userDao.detail(id);
    Account account = accountDao.select(id);
    return new UserDetailDTO()
      .setId(id)
      .setEmail(account.getEmail())
      .setUsername(account.getUsername())
      .setGender(detail.getGender())
      .setAvatar(detail.getAvatar())
      .setPublish(userDao.publishCount(id))
      .setStar(userDao.starCount(id));
  }

  private List<BlogItem> items(List<Object> t) {
    List<BlogItem> items = new ArrayList<>();
    t.forEach(b -> {
      long blogId = 0, accountId = 0;
      if (b instanceof Blog) {
        blogId = ((Blog) b).getId();
        accountId = ((Blog) b).getAccountId();
      } else if (b instanceof StarBlog) {
        blogId = ((StarBlog) b).getBlogId();
        accountId = ((StarBlog) b).getAccountId();
      } else if (b instanceof BlogHistory) {
        blogId = ((BlogHistory) b).getBlogId();
        accountId = ((BlogHistory) b).getAccountId();
      } else {
        throw new BusinessException(Result.UNKNOWN_ERROR);
      }
      Blog blog = blogDao.select(blogId);
      Account account = accountDao.select(accountId);
      BlogItem item = new BlogItem()
        .setId(blog.getId())
        .setAuthor(account.getUsername())
        .setTitle(blog.getTitle())
        .setLikes(blog.getLike())
        .setStars(blog.getStar())
        .setUpdateAt(blog.getUpdateAt());
      items.add(item);
    });
    return items;
  }

  public List<BlogItem> history(long id) {
    List<BlogHistory> histories = userDao.history(id);
    return items(Collections.singletonList(histories));
  }

  public List<BlogItem> star(long id) {
    List<StarBlog> starBlogs = userDao.star(id);
    return items(Collections.singletonList(starBlogs));
  }

  public List<BlogItem> publish(long id) {
    List<Blog> blogs = userDao.publish(id);
    return items(Collections.singletonList(blogs));
  }

  public List<BlogItem> draft(long id) {
    List<Blog> blogs = userDao.draft(id);
    return items(Collections.singletonList(blogs));
  }

  public int drafts(long id) {
    return userDao.draftCount(id);
  }

  public int histories(long id) {
    return userDao.historyCount(id);
  }

  public int publishes(long id) {
    return userDao.publishCount(id);
  }

  public int stars(long id) {
    return userDao.starCount(id);
  }
}
