package alanisia.blog.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Operation {
  private int id;
  private String operation;
  private Date createAt;
  private Date updateAt;
}
