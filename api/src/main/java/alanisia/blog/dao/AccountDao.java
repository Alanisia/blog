package alanisia.blog.dao;

import alanisia.blog.model.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountDao {
  @Insert("insert into account(role_id, email, username, password)" +
      " values(#{account.roleId}, #{account.email}, #{account.username}, #{account.password})")
  void insert(@Param("account") Account account);

  @Delete("delete from account where id = #{id}")
  void delete(@Param("id") long id);

  @Update("update account set role_id = #{account.roleId}, email = #{account.email}, " +
      "username = #{account.username}, password = #{account.password} where id = #{id}")
  void update(@Param("id") long id, @Param("account") Account account);

  @Select("select * from account where id = #{id}")
  Account select(@Param("id") long id);

  @Select("select * from account where email = #{email}")
  Account getByEmail(@Param("email") String email);

  @Select("select * from account")
  List<Account> getAll();
}
