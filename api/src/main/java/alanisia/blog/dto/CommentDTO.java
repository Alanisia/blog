package alanisia.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class CommentDTO implements Serializable {
  private long id;
  private long accountId;
  private long blogId;
  private long commentId;
  private long targetId;
  private String commenter;
  private String target;
  private String content;
  private Date createAt;
}
