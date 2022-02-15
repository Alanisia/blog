package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CommentVO implements Serializable {
  private static final long serialVersionUID = 7135624312721088788L;
  private long accountId;
  private long blogId;
  private String content;
}
