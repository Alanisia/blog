package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class StarVO implements Serializable {
  private long accountId;
  private long blogId;
}
