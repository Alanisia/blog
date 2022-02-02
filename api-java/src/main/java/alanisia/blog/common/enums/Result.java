package alanisia.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Result {
  OK(0, "OK"),

  ACCOUNT_NOT_FOUND(1, "Account not found"),
  ACCOUNT_EXISTED(2, "Account existed"),
  PASSWORD_INCORRECT(4, "Password incorrect"),
  AUTHORIZE_FAILED(8, "Authorize failed"),
  CAPTCHA_ERROR(10, "Captcha error"),
  TOKEN_EXPIRED(15, "Token expired"),
  NO_PERMISSION(16, "No permission");

  private final int code;
  private final String message;
}
