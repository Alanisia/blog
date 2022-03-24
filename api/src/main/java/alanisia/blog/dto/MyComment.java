package alanisia.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class MyComment implements Serializable {
  private static final long serialVersionUID = -2764711274366996937L;
  private long id;
  private String target;
  private String title;
  private long blogId;
  private Date createAt;
}
