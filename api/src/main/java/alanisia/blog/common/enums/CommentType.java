package alanisia.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentType {
  COMMENT(1), REPLY(2);
  private final int type;
}
