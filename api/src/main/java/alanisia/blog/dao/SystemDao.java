package alanisia.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SystemDao {
  @Select("select count(*) from account")
  int memberCount();
}
