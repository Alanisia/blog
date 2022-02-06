package alanisia.blog.dao;

import alanisia.blog.model.Blog;
import alanisia.blog.model.BlogHistory;
import alanisia.blog.model.StarBlog;
import alanisia.blog.model.UserDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
  @Insert("insert into user_detail(account_id, gender, avatar) " +
    "values(#{detail.accountId}), #{detail.gender}, #{detail.avatar})")
  void insert(@Param("detail") UserDetail detail);

  @Update("update user_detail set gender = #{detail.gender}, avatar = #{detail.avatar}" +
    " where account_id = #{detail.accountId}")
  void update(@Param("detail") UserDetail detail);

  @Select("select * from user_detail where id = #{id}")
  UserDetail detail(@Param("id") long id);

  @Select("select * from blog_history where account_id = #{accountId}")
  List<BlogHistory> history(@Param("accountId") long accountId);

  @Select("select count(*) from blog_history where account_id = #{accountId}")
  int historyCount(@Param("accountId") long accountId);

  @Select("select * from blog where account_id = #{account_id} and draft = true")
  List<Blog> draft(@Param("accountId") long accountId);

  @Select("select count(*) from blog where account_id = #{accountId} and draft = true")
  int draftCount(@Param("accountId") long accountId);

  @Select("select * from star_blog where account_id = #{accountId}")
  List<StarBlog> star(@Param("accountId") long accountId);

  @Select("select count(*) from star_blog where account_id = #{accountId}")
  int starCount(@Param("accountId") long accountId);

  @Select("select * from blog where account_id = #{id}")
  List<Blog> publish(@Param("id") long id);

  @Select("select count(*) from blog where account_id = #{id}")
  int publishCount(@Param("id") long id);
}
