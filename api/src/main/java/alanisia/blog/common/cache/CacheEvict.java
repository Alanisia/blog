package alanisia.blog.common.cache;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheEvict {
  String value();
  long key() default 0;
}
