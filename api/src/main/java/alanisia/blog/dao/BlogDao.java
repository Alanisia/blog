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

  @Insert("insert into star_blog(`account_id`, `blog_id`) values(#{accountId}, #{blogId})")
  void star(@Param("accountId") long accountId, @Param("blogId") long blogId);

  @Delete("delete from star_blog where account_id = #{accountId} and blog_id = #{blogId}")
  void cancelStar(@Param("accountId") long accountId, @Param("blogId") long blogId);

  @Insert("insert into like_blog(`account_id`, `blog_id`) values(#{accountId}, #{blogId})")
  void like(@Param("accountId") long accountId, @Param("blogId") long blogId);

  @Delete("delete from like_blog where account_id = #{accountId} and blog_id = #{blogId}")
  void cancelLike(@Param("accountId") long accountId, @Param("blogId") long blogId);

  Blog select(long id);

  List<Blog> blogs();

  List<Blog> newest(int count);

  List<Blog> selectByCategory(int id, int limit, int offset);

  @Select("select count(*) from blog")
  int count();

  @Select("select count(*) from blog where category_id = #{id}")
  int countOfCategory(@Param("id") int id);
}
