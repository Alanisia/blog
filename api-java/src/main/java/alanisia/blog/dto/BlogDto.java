package alanisia.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class BlogDto implements Serializable {
  private long id;
  private String title;
  private String author;
  private int likes;
  private int stars;
  private int comments;
  private Date updateAt;
}
