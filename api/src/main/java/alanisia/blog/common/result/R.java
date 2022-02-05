package alanisia.blog.common.result;

import alanisia.blog.common.enums.Result;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class R {
  int code;
  String message;
  Object data;

  public R(Result result) {
    this.code = result.getCode();
    this.message = result.getMessage();
  }

  public static R ok() {
    return new R(Result.OK);
  }

  public static R error(Result result) {
    return new R(result);
  }
}
