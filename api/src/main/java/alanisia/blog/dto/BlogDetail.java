package alanisia.blog.dto;

import alanisia.blog.model.Blog;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Setter
@Getter
@Accessors(chain = true)
public class BlogDetail extends Blog implements Serializable {
  private String author;
  private String category;
  private int comments;
}
