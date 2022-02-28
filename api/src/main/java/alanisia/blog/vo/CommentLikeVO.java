package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CommentLikeVO implements Serializable {
  private static final long serialVersionUID = -2973131639407040548L;
  private long accountId;
  private long commentId;
}
