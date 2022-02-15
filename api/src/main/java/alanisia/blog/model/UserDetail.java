package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserDetail {
  private long id;
  private long accountId;
  private String gender;
  private Date createAt;
  private Date updateAt;
}
