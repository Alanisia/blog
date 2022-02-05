package alanisia.blog.exception;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {
  @ExceptionHandler(BusinessException.class)
  public R error() {
    try {
      Method method = getClass().getMethod("error");
      Annotation annotation = AnnotationUtils.findAnnotation(method, ExceptionHandler.class);
      BusinessException x = (BusinessException) AnnotationUtils.getValue(annotation);
      assert x != null;
      Result result = x.getResult();
      return R.error(result);
    } catch (NoSuchMethodException | NullPointerException e) {
      log.error("{}", e.getMessage());
      return R.error(Result.UNKNOWN_ERROR);
    }
  }
}
