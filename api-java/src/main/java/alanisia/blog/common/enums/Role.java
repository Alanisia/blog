package alanisia.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
  SUPER(1, "Super administrator"),
  ADMIN(2, "Administrator"),
  MEMBER(3, "Member");

  private final int id;
  private final String name;
}
