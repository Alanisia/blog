package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AccountIdAndBlogId implements Serializable {
  private static final long serialVersionUID = -3243631521494793270L;
  private long accountId;
  private long blogId;
}
