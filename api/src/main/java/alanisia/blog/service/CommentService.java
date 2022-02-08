package alanisia.blog.service;

import alanisia.blog.dao.CommentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommentService {
  @Autowired private CommentDao commentDao;
}
