package alanisia.blog.common.enums;

import lombok.Getter;

@Getter
public enum Result {
  OK(0, "OK"),

  ACCOUNT_NOT_FOUND(1, "Account not found"),
  ACCOUNT_EXISTED(2, "Account existed"),
  PASSWORD_INCORRECT(4, "Password incorrect"),
  AUTHORIZE_FAILED(8, "Authorize failed"),
  CAPTCHA_ERROR(10, "Captcha error"),
  NO_PERMISSION(16, "No permission");

  private final int code;
  private final String message;

  Result(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
