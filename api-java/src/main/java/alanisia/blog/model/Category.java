package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Category {
  private int id;
  private String name;
  private Date createAt;
  private Date updateAt;
}
