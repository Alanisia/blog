package alanisia.blog.vo;

import alanisia.blog.model.Category;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors
public class BlogVO implements Serializable {
  private static final long serialVersionUID = -6889046327620126876L;
  private long authorId;
  private String title;
  private Category category;
  private String content;
  private int draft;
}
