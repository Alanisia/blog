package alanisia.blog.dao;

import alanisia.blog.model.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogDao {
  void insert(@Param("blog") Blog blog);

  @Delete("delete from blog where id = #{id}")
  void delete(@Param("id") long id);

  @Select("select * from blog where id = #{id}")
  Blog select(@Param("id") long id);

  @Select("select * from blog")
  List<Blog> blogs();

  @Select("select title, stars, likes, comments, from order by create_at desc")
  List<Blog> newest();

  List<Blog> results();

  // List<Blog>
}
