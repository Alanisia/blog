package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors
public class BlogVO implements Serializable {
  private static final long serialVersionUID = 5508751240279613273L;
  private long authorId;
  private String title;
  private int categoryId;
  private String content;
  private int draft;
}
