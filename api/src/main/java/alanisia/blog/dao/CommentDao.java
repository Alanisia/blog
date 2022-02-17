package alanisia.blog.dao;

import alanisia.blog.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDao {
  @Insert("insert into comment(`account_id`, `blog_Id`, `comment_id`, `target_id`, `content`, `like`) " +
    "values(#{comment.accountId}, #{comment.blogId}, #{comment.commentId}, #{comment.targetId}, " +
    "#{comment.content}, #{comment.like})")
  @SelectKey(before = false, keyColumn = "id", keyProperty = "id", resultType = Long.class,
    statement = "select last_insert_id()")
  void insert(@Param("comment") Comment comment);

  @Delete("delete from comment where id = #{id}")
  void delete(@Param("id") long id);

  @Delete("delete from comment where blog_id = #{blogId}")
  void deleteByBlogId(@Param("blogId") long blogId);

  // TODO: have not decided if use this method to update likes
  @Update("update comment set like = like + 1 where id = #{id}")
  void like(@Param("id") long id);

  @Select("select * from comment where blog_id = #{blogId}")
  List<Comment> comments(@Param("blogId") long blogId);

  @Select("select * from comment where comment_id = #{commentId}")
  List<Comment> replies(@Param("commentId") long commentId);
}
