package alanisia.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class BlogInfo implements Serializable {
  private static final long serialVersionUID = -4195633090822530099L;
  private int count;
  private List<BlogItem> items;
}
