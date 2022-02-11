package alanisia.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class BlogPagination implements Serializable {
  private static final long serialVersionUID = 6430717092850568441L;
  private int pages;
  private int currentPage;
  private List<BlogItem> items;
}
