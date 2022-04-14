package alanisia.blog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Logout implements Serializable {
  private static final long serialVersionUID = -614152218878306061L;
  private long id;
}
