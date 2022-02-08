package alanisia.blog.dao;

import alanisia.blog.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDao {
  @Insert("insert into comment() values()")
  void insert(@Param("comment") Comment comment);

  @Delete("delete from comment where id = #{id}")
  void delete(@Param("id") long id);

  @Update("update comment set like = like + 1 where id = #{id}")
  void like(@Param("id") long id);

  // List<CommentDto> select();
}
