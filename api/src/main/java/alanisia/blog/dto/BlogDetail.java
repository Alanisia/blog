package alanisia.blog.dto;

import alanisia.blog.model.Category;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class BlogDetail implements Serializable {
  private static final long serialVersionUID = 5998211047757680172L;
  private long id;
  private String author;
  private Category category;
  private String title;
  private String content;
  private int likes;
  private int stars;
  private int comments;
  private Date updateTime;
}
