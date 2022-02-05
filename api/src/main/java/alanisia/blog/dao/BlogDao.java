package alanisia.blog.dao;

import alanisia.blog.model.Blog;
import alanisia.blog.model.BlogHistory;
import alanisia.blog.model.StarBlog;
import alanisia.blog.model.TagBlog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogDao {
  @Insert("insert into blog(account_id, category_id, title, content, draft, like, star" +
    " values(#{blog.accountId}, #{blog.categoryId}, #{blog.title}, #{blog.content}, " +
    "#{blog.draft}, #{blog.like}, #{blog.star}")
  @SelectKey(before = false, keyColumn = "id", keyProperty = "id", resultType = Long.class,
    statement = "select last_insert_id()")
  void insert(@Param("blog") Blog blog);

  @Delete("delete from blog where id = #{id}")
  void delete(@Param("id") long id);

  // TODO: update blog
  @Update("update blog set [TODO] where id = #{id}")
  void update(@Param("id") long id, @Param("blog") Blog blog);

  @Insert("insert into tag_blog(blog_id, tag_id) values(#{tagBlog.blogId}, #{tagBlog.tagId})")
  void insertTagBlog(@Param("tagBlog") TagBlog tagBlog);

  @Select("select * from tag_blog where blog_id = #{blogId}")
  List<TagBlog> getBlogsByTag(long blogId);

  @Select("select * from blog where id = #{id}")
  Blog select(@Param("id") long id);

  @Select("select * from blog")
  List<Blog> blogs();

  @Select("select * from blog order by create_at desc limit #{count}")
  List<Blog> newest(@Param("count") int count);


}
