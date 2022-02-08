package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UpdateBlogVO implements Serializable {
  private long id;
  private BlogVO blog;
}
