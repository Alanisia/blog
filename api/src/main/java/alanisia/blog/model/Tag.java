package alanisia.blog.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Tag implements Serializable {
  private int id;
  private String name;
  private transient Date createAt;
  private transient Date updateAt;
}
