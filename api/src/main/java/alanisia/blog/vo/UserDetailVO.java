package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserDetailVO implements Serializable {
  private static final long serialVersionUID = 9112930263091579171L;
  private long id;
  private String username;
  private String gender;
}
