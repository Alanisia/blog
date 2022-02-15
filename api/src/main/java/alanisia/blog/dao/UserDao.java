package alanisia.blog.dao;

import alanisia.blog.model.Blog;
import alanisia.blog.model.BlogHistory;
import alanisia.blog.model.StarBlog;
import alanisia.blog.model.UserDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
  @Insert("insert into user_detail(account_id, gender) values(#{detail.accountId}, #{detail.gender})")
  void insert(@Param("detail") UserDetail detail);

  @Update("update user_detail set gender = #{detail.gender} where account_id = #{detail.accountId}")
  void update(@Param("detail") UserDetail detail);

  @Select("select * from user_detail where id = #{id}")
  @Results({
    @Result(column = "id", property = "id"),
    @Result(column = "account_id", property = "accountId"),
    @Result(column = "gender", property = "gender"),
    @Result(column = "create_at", property = "createAt"),
    @Result(column = "update_at", property = "updateAt")
  })
  UserDetail detail(@Param("id") long id);

  @Select("select * from blog_history where account_id = #{accountId}")
  @Results({
    @Result(column = "id", property = "id"),
    @Result(column = "account_id", property = "accountId"),
    @Result(column = "blog_id", property = "blogId"),
    @Result(column = "create_at", property = "createAt")
  })
  List<BlogHistory> history(@Param("accountId") long accountId);

  @Select("select count(*) from blog_history where account_id = #{accountId}")
  int historyCount(@Param("accountId") long accountId);

  @Insert("insert into blog_history(account_id, blog_id) values(#{accountId}, #{blogId})")
  void insertHistory(@Param("accountId") long accountId, @Param("blogId") long blogId);

  @Delete("delete from blog_history where account_id = #{accountId} and blog_id = #{blogId}")
  void removeHistory(@Param("accountId") long accountId, @Param("blogId") long blogId);

  @Select("select count(*) from blog_history where account_id = #{accountId} and blog_id = #{blogId}")
  int historyExists(@Param("accountId") long accountId, @Param("blogId") long blogId);

  List<Blog> draft(long accountId);

  @Select("select count(*) from blog where account_id = #{accountId} and draft = 1")
  int draftCount(@Param("accountId") long accountId);

  @Select("select * from star_blog where account_id = #{accountId}")
  @Results({
    @Result(column = "id", property = "id"),
    @Result(column = "account_id", property = "accountId"),
    @Result(column = "blog_id", property = "blogId"),
    @Result(column = "create_at", property = "createAt")
  })
  List<StarBlog> star(@Param("accountId") long accountId);

  @Select("select count(*) from star_blog where account_id = #{accountId}")
  int starCount(@Param("accountId") long accountId);

  List<Blog> publish(long accountId);

  @Select("select count(*) from blog where account_id = #{id} and draft = 0")
  int publishCount(@Param("id") long id);
}
