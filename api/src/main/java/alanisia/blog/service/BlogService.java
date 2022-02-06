package alanisia.blog.service;

import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.dao.AccountDao;
import alanisia.blog.dao.BlogDao;
import alanisia.blog.dao.CategoryDao;
import alanisia.blog.dto.BlogDetail;
import alanisia.blog.dto.BlogItem;
import alanisia.blog.model.Account;
import alanisia.blog.model.Blog;
import alanisia.blog.model.Tag;
import alanisia.blog.model.TagBlog;
import alanisia.blog.vo.BlogVO;
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

  public void createBlog(BlogVO vo) {
    log.debug("create blog: blog = {}", JsonUtil.json(vo));
    Blog blog = new Blog()
      .setAccountId(vo.getAuthorId())
      .setCategoryId(vo.getCategory().getId())
      .setTitle(vo.getTitle())
      .setContent(vo.getContent())
      .setLike(0).setStar(0);
    blogDao.insert(blog);
    vo.getTags().forEach(c -> {
      TagBlog tagBlog = new TagBlog();
      tagBlog.setBlogId(blog.getId()).setTagId(c.getId());
      blogDao.insertTagBlog(tagBlog);
    });
  }

  public void deleteBlog(long id) {
    log.debug("delete blog: id = {}", id);
    blogDao.delete(id);
  }

  public void updateBlog(long id) {
    log.debug("update blog: id = {}", id);
  }

  @Cacheable(value = "newest")
  public List<BlogItem> ListOfNewest() {
    List<Blog> newestBlogs = blogDao.newest(50);
    List<BlogItem> items = new ArrayList<>();
    newestBlogs.forEach(blog -> {
      BlogItem item = new BlogItem()
        .setAuthor(accountDao.select(blog.getAccountId()).getUsername())
        .setTitle(blog.getTitle()).setStars(blog.getStar())
        .setLikes(blog.getLike());
      items.add(item);
    });
    return items;
  }

  @Cacheable(value = "blog_detail", key = "#id")
  public BlogDetail detail(long id) {
    Blog blog = blogDao.select(id);
    List<TagBlog> tagBlogs = blogDao.getBlogsByTag(id);
    List<Tag> tags = new ArrayList<>();
    tagBlogs.forEach(c -> {
      Tag tag = categoryDao.tag(c.getTagId());
      tags.add(tag);
    });
    Account account = accountDao.select(blog.getAccountId());
    return (BlogDetail) new BlogDetail()
      .setAuthor(account.getUsername())
      .setCategory(categoryDao.category(blog.getCategoryId()).getName())
      .setTags(tags)
      .setContent(blog.getContent());
  }
}
