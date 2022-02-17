package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ReplyVO implements Serializable {
  private static final long serialVersionUID = -4841983339482400900L;
  private long accountId;
  private long blogId;
  private long commentId;
  private long targetId;
  private String content;
}
