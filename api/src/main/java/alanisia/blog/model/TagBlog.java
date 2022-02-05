package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class TagBlog {
  private long id;
  private long blogId;
  private int tagId;
  private Date createAt;
  private Date updateAt;
}
