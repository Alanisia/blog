package alanisia.blog.exception;

import alanisia.blog.common.result.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
  @ExceptionHandler(BusinessException.class)
  public R error(BusinessException e) {
    return R.error(e.getResult());
  }
}
