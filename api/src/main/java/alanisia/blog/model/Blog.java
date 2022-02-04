package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Blog {
  private long id;
  private long accountId;
  private int categoryId;
  private String title;
  private String content;
  private int like;
  private Date createAt;
  private Date updateAt;
}
