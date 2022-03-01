package alanisia.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class StarredOrLiked implements Serializable {
  private static final long serialVersionUID = 5671629644940584584L;
  private boolean haveStarred, haveLiked;
}
