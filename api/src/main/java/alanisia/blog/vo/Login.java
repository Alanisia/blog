package alanisia.blog.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Login implements Serializable {
  private String email;
  private String password;
  private String captchaCode;
  private String captchaImage;
}
