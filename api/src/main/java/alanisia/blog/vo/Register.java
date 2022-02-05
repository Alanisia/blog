package alanisia.blog.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Register implements Serializable {
  private String email;
  private String username;
  private String password;
  private String captchaCode;
  private String captchaImage;
}
