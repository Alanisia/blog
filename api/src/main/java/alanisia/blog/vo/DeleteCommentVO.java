package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class DeleteCommentVO implements Serializable {
  private static final long serialVersionUID = -5327207127641999363L;
  private long blogId;
  private long commentId;
  private long replyId;
}
