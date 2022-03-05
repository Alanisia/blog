package alanisia.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CommentLiked implements Serializable {
  private static final long serialVersionUID = 8576406203182946414L;
  private boolean liked;
}
