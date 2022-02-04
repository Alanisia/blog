package alanisia.blog.dao;

import alanisia.blog.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionDao {
  @Insert("insert into role(id, name) values(#{role.id}, #{role.name})")
  void insertRole(@Param("role") Role role);

  @Update("update role set name = #{role.name} where id = #{id}")
  void updateRole(@Param("id") int id, @Param("role") Role role);

  @Select("select * from role where id = #{id}")
  Role selectRole(@Param("id") int id);

  @Select("select * from role")
  List<Role> roles();
}
