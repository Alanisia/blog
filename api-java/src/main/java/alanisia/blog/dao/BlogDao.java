package alanisia.blog.dao;

import alanisia.blog.dto.BlogDto;
import alanisia.blog.model.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BlogDao {
  @Insert("insert into blog(account_id, category_id, title, content, like, star, create_at, update_at)" + 
   "values(#{blog.accountId}, #{blog.categoryId}, #{blog.title}, #{blog.content}, #{blog.like}, " + 
   "#{blog.star}, #{blog.createAt}, #{blog.updateAt})")
  void insert(@Param("blog") Blog blog);

  @Delete("delete from blog where id = #{id}")
  void delete(@Param("id") long id);

  @Update("update blog set [TODO] where id = #{id}")
  void update(@Param("id") long id, @Param("blog") Blog blog);

  @Select("select * from blog where id = #{id}")
  Blog select(@Param("id") long id);

  @Select("select * from blog")
  List<Blog> blogs();

  @Select("select title, stars, likes, comments, from order by create_at desc")
  List<Blog> newest();

  List<Blog> results();

  List<BlogDto> dtos();
}
