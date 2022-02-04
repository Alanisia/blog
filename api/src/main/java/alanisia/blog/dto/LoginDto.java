package alanisia.blog.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginDto implements Serializable {
  private String email;
  private String password;
  private String captchaCode;
  private String captchaImage;
}
