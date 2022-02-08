package alanisia.blog.vo;

import alanisia.blog.model.Category;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors
public class BlogVO implements Serializable {
  private long authorId;
  private String title;
  private Category category;
  private String content;
  private int draft;
}
