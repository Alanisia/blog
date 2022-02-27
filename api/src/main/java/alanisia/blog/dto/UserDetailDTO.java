package alanisia.blog.dto;

import alanisia.blog.common.cache.CacheGetId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserDetailDTO implements Serializable, CacheGetId {
  private static final long serialVersionUID = 8363936218407846286L;
  private long id;
  private String email;
  private String username;
  private String gender;
  private int publish;
  private int star;
}
