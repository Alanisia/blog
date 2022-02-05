package alanisia.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserDetailDTO implements Serializable {
  private long id;
  private String email;
  private String username;
  private String gender;
  private String avatar;
  private int publish;
  private int star;
}
