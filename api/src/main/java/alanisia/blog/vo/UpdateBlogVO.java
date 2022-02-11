package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UpdateBlogVO implements Serializable {
  private static final long serialVersionUID = -5996752151419049679L;
  private long id;
  private BlogVO blog;
}
