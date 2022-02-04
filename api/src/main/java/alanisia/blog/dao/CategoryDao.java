package alanisia.blog.dao;

import alanisia.blog.model.Category;
import alanisia.blog.model.Tag;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryDao {
  @Select("select * from category")
  List<Category> categories();

  @Insert("insert into category(`name`, `route`) values(#{category.name}, #{category.route})")
  void insertCategory(@Param("category") Category category);

  @Select("select * from tag")
  List<Tag> tags();

  @Insert("insert into tag(`name`) values(#{tag.name})")
  void insertTag(@Param("tag") Tag tag);
}
