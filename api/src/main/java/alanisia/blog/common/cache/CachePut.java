package alanisia.blog.common.cache;

import java.lang.annotation.*;

@Deprecated
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CachePut {
  long expire() default 30 * 60 * 1000;
  String value();
  long key() default 0;
}
