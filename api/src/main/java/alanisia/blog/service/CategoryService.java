package alanisia.blog.service;

import alanisia.blog.dao.CategoryDao;
import alanisia.blog.model.Category;
import alanisia.blog.model.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {
  @Autowired private CategoryDao categoryDao;

  @Cacheable(value = "categories")
  public List<Category> categories() {
    return categoryDao.categories();
  }

  @Cacheable(value = "tags")
  public List<Tag> tags() {
    return categoryDao.tags();
  }
}
