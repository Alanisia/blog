package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Comment {
  private long id;
  private long commentId;
  private long targetId;
  private long accountId;
  private long blogId;
  private int like;
  private String content;
  private Date createAt;
}
