package alanisia.blog.dto;

import alanisia.blog.common.cache.CacheGetId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class CommentDTO implements Serializable, CacheGetId {
  private static final long serialVersionUID = -4236899487843283673L;
  private long id;
  private long accountId;
  private long blogId;
  private long commentId;
  private long targetId;
  private String commenter;
  private String target;
  private String content;
  private int like;
  private Date createAt;
}
