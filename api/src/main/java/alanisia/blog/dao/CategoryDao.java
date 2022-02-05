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

  @Select("select * from category where id = #{id}")
  Category category(int id);

  @Insert("insert into category(`name`, `route`) values(#{category.name}, #{category.route})")
  void insertCategory(@Param("category") Category category);

  @Select("select * from tag")
  List<Tag> tags();

  @Select("select * from tag where id = #{id}")
  Tag tag(int id);

  @Insert("insert into tag(`name`) values(#{tag.name})")
  void insertTag(@Param("tag") Tag tag);
}
