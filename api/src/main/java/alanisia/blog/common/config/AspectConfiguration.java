package alanisia.blog.common.config;

import alanisia.blog.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectConfiguration {
  @Pointcut("execution (* alanisia.blog.controller.*.*(..))")
  public void pointcut() {}

  @Before("pointcut()")
  public void before(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String method = signature.getMethod().getName();
    Object[] args = joinPoint.getArgs();
    log.info("Method = {}, args = {}", method, JsonUtil.json(args));
  }
}
