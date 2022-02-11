package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserDetailVO implements Serializable {
  private static final long serialVersionUID = -3677064335950454148L;
  private String username;
  private String gender;
}
