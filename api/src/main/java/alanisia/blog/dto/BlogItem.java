package alanisia.blog.dto;

import alanisia.blog.common.cache.CacheGetId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class BlogItem implements Serializable, CacheGetId {
  private static final long serialVersionUID = -8114646902840273086L;
  private long id;
  private String category;
  private String title;
  private String author;
  private int like;
  private int star;
  private int comment;
  private Date updateAt;
}
