package alanisia.blog.common.cache;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.util.CacheUtil;
import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Deprecated
//@Aspect
//@Component
public class CacheAspect {
  @Pointcut("@annotation(alanisia.blog.common.cache.Cache)")
  public void cache() {}

  @Pointcut("@annotation(alanisia.blog.common.cache.CachePut)")
  public void cachePut() {}

  @Pointcut("@annotation(alanisia.blog.common.cache.CacheEvict)")
  public void cacheEvict() {}

  @Before("cache()")
  public Object get(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    Cache cache = method.getAnnotation(Cache.class);
    String key = cache.value() + "::" + cache.key();
    log.debug("Key = {}", key);
    Object value = CacheUtil.get(key);
    log.debug("Get cache: {}", JsonUtil.json(value));
    return value == null ? joinPoint.proceed() : value;
  }

  @AfterReturning("cachePut()")
  public void put(JoinPoint joinPoint, Object returnValue) {
    log.debug("Put cache: {}", JsonUtil.json(returnValue));
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    CachePut cache = method.getAnnotation(CachePut.class);
    String name = cache.value();
    long key;
    if (returnValue instanceof CacheGetId) key = ((CacheGetId) returnValue).getId();
    else throw new BusinessException(Result.UNKNOWN_ERROR);
    CacheUtil.put(name + "::" + key, returnValue);
  }

  @After("cacheEvict()")
  public void evict(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    CacheEvict cache = method.getAnnotation(CacheEvict.class);
    String key = cache.value() + "::" + cache.key();
    log.debug("Key = {}", key);
    CacheUtil.remove(key);
  }
}
