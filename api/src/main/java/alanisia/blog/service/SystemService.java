package alanisia.blog.service;

import alanisia.blog.dao.BlogDao;
import alanisia.blog.dao.SystemDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SystemService {
  @Autowired private BlogDao blogDao;
  @Autowired private SystemDao systemDao;

  @Cacheable(value = "blog_count")
  public int blogCount() {
    return blogDao.count();
  }

  @Cacheable(value = "member_count")
  public int memberCount() {
    return systemDao.memberCount();
  }
}
