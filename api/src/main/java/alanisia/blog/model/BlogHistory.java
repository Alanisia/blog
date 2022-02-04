package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class BlogHistory {
  private long id;
  private long blogId;
  private long accountId;
  private Date createAt;
}
