package alanisia.blog.dto;

import alanisia.blog.model.Blog;
import alanisia.blog.model.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class BlogDetail extends Blog implements Serializable {
  private String author;
  private String category;
  private List<Tag> tags;
  private int comments;
}
