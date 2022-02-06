package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserDetailVO implements Serializable {
  private String username;
  private String gender;
  private String avatar;
}
