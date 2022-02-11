package alanisia.blog.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Login implements Serializable {
  private static final long serialVersionUID = 1801606365513387686L;
  private String email;
  private String password;
  private String captchaCode;
  private String captchaImage;
}
