package alanisia.blog.exception;

import alanisia.blog.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {
  @ExceptionHandler(BusinessException.class)
  public R error(BusinessException e) {
    StringBuilder builder = new StringBuilder();
    Arrays.asList(e.getStackTrace()).forEach(c -> builder.append(c.toString()).append('\n'));
    log.warn("{}", builder.toString());
    return R.error(e.getResult());
  }
}
