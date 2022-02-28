package alanisia.blog.common.cache;

import java.lang.annotation.*;

@Deprecated
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
  String value();
  long key();
}
