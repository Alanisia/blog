package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Category implements Serializable {
  private int id;
  private String name;
  private String route;
  private transient Date createAt;
  private transient Date updateAt;
}
