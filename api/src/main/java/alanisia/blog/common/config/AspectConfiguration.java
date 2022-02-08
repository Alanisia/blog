package alanisia.blog.common.config;

import alanisia.blog.common.util.JsonUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class AspectConfiguration {
  @Pointcut("execution (* alanisia.blog.controller.*.*(..))")
  public void pointcut() {}

  @Before("pointcut()")
  public void beforeController(JoinPoint joinPoint) {
    Info info = before(joinPoint);
    log.debug("{}.{}, args = {}", info.getClazz(), info.getMethod(), JsonUtil.json(info.getArgs()));
  }

  @Before("execution (* alanisia.blog.exception.ExceptionsHandler.*(..))")
  public void beforeException(JoinPoint joinPoint) {
    Info info = before(joinPoint);
    log.debug("{}.{}, args = {}", info.getClazz(), info.getMethod(), info.getArgs());
  }

  private Info before(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method m = signature.getMethod();
    String method = m.getName();
    String clazz = m.getDeclaringClass().getSimpleName();
    Object[] args = joinPoint.getArgs();
    return new Info().setClazz(clazz).setMethod(method).setArgs(args);
    // log.info("Class = {}, method = {}", clazz, method);
    // log.debug("Args = {}", JsonUtil.json(args));
  }

  @Data
  @Accessors(chain = true)
  static class Info {
    private String clazz, method;
    private Object[] args;
  }
}
