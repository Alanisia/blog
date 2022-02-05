package alanisia.blog.vo;

import alanisia.blog.model.Category;
import alanisia.blog.model.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors
public class BlogVO implements Serializable {
  private long authorId;
  private String title;
  private Category category;
  private List<Tag> tags;
  private String content;
}
