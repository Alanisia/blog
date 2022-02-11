package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Blog implements Serializable {
  private static final long serialVersionUID = 8410851282490572925L;
  private long id;
  private long accountId;
  private int categoryId;
  private String title;
  private String content;
  private int draft;
//  private int star;
//  private int like;
  private Date createAt;
  private Date updateAt;
}
