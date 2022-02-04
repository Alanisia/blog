package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Account {
  private long id;
  private int roleId;
  private String email;
  private String username;
  private String password;
  private Date createAt;
  private Date updateAt;
}
