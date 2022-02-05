package alanisia.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Token implements Serializable {
  private String token;
  private long accountId;
}
