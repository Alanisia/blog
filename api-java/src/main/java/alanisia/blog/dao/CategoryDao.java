package alanisia.blog.dao;

import alanisia.blog.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryDao {
  @Select("select * from category")
  List<Category> categories();

  // List<Tag>
}
