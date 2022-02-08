package alanisia.blog.service;

import alanisia.blog.dao.CategoryDao;
import alanisia.blog.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
  @Autowired
  private CategoryDao categoryDao;

  @Cacheable(value = "categories")
  public List<Category> categories() {
    return categoryDao.categories();
  }
}
