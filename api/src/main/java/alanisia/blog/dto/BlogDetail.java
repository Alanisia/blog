package alanisia.blog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Accessors(chain = true)
public class BlogDetail implements Serializable {
  private long id;
  private String author;
  private String category;
  private String title;
  private String content;
  private int likes;
  private int stars;
  private int comments;
  private Date updateTime;
}
