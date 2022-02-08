package alanisia.blog.dao;

import alanisia.blog.model.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogDao {
  @Insert("insert into blog(`account_id`, `category_id`, `title`, `content`, `draft`, `like`, `star`)" +
    " values(#{blog.accountId}, #{blog.categoryId}, #{blog.title}, #{blog.content}, " +
    "#{blog.draft}, #{blog.like}, #{blog.star})")
  @SelectKey(before = false, keyColumn = "id", keyProperty = "id", resultType = Long.class,
    statement = "select last_insert_id()")
  void insert(@Param("blog") Blog blog);

  @Delete("delete from blog where `id` = #{id}")
  void delete(@Param("id") long id);

  @Update("update blog set `account_id` = #{blog.accountId}, `category_id` = #{blog.categoryId}," +
    " `title` = #{blog.title}, `content` = #{blog.content}, `draft` = #{blog.draft}, " +
    " `like` = #{blog.like}, `star` = #{blog.star} where `id` = #{id}")
  void update(@Param("id") long id, @Param("blog") Blog blog);

  @Select("select * from blog where `id` = #{id}")
  Blog select(@Param("id") long id);

  @Select("select * from blog")
  List<Blog> blogs();

  @Select("select * from blog order by `create_at` desc limit #{count}")
  List<Blog> newest(@Param("count") int count);
}
