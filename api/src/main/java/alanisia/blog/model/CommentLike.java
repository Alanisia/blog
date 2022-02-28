package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class CommentLike implements Serializable {
  private static final long serialVersionUID = -81016085949993019L;
  private long id;
  private long accountId;
  private long commentId;
  private Date createAt;
}
