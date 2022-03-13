package alanisia.blog.dao;

import alanisia.blog.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDao {
  @Insert("insert into comment(`account_id`, `blog_Id`, `comment_id`, `target_id`, `content`) " +
    "values(#{comment.accountId}, #{comment.blogId}, #{comment.commentId}, #{comment.targetId}, #{comment.content})")
  @SelectKey(before = false, keyColumn = "id", keyProperty = "id", resultType = Long.class,
    statement = "select last_insert_id()")
  void insert(@Param("comment") Comment comment);

  @Delete("delete from comment where id = #{id}")
  void delete(@Param("id") long id);

  @Delete("delete from comment where blog_id = #{blogId}")
  void deleteByBlogId(@Param("blogId") long blogId);

  @Insert("insert into comment_like(`account_id`, `comment_id`) values(#{accountId}, #{commentId})")
  void like(@Param("accountId") long accountId, @Param("commentId") long commentId);

  @Delete("delete from comment_like where `account_id` = #{accountId} and `comment_id` = #{commentId}")
  void cancelLike(@Param("accountId") long accountId, @Param("commentId") long commentId);

  @Select("select count(*) from comment_like where `account_id` = #{accountId} and `comment_id` = #{commentId}")
  int liked(@Param("accountId") long accountId, @Param("commentId") long commentId);

  @Select("select count(*) from comment_like where `comment_id` = #{commentId}")
  int likes(@Param("commentId") long commentId);

  @Delete("delete from comment_like where `comment_id` = #{commentId}")
  void removeLikes(@Param("commentId") long commentId);

  List<Comment> comments(@Param("blogId") long blogId);

  @Select("select count(*) from comment where blog_id = #{blogId}")
  int commentCount(@Param("blogId") long blogId);

  @Select("select count(*) from comment where comment_id = #{commentId}")
  int replyCount(@Param("commentId") long commentId);

  List<Comment> replies(@Param("commentId") long commentId);

  Comment select(@Param("id") long id);
}
