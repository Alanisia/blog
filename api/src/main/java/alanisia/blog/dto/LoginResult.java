package alanisia.blog.dto;

import java.io.Serializable;

import alanisia.blog.common.enums.Result;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginResult {
  private Result result;
  private LoginDto dto;

  @Data
  @Accessors(chain = true)
  public static class LoginDto implements Serializable {
    private String token;
    private long accountId;
  }
}
