package alanisia.blog.model;

import alanisia.blog.service.UserService;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class BlogHistory implements UserService.GetBlogId {
  private long id;
  private long blogId;
  private long accountId;
  private Date createAt;
}
