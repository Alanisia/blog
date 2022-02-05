package alanisia.blog.exception;

import alanisia.blog.common.enums.Result;

public class BusinessException extends RuntimeException {
  private Result result;

  public BusinessException() {
    super();
  }

  public BusinessException(Result result) {
    super();
    this.result = result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public Result getResult() {
    return result;
  }
}
