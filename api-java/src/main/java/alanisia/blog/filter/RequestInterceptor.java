package alanisia.blog.filter;

import alanisia.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {
  @Autowired private AccountService accountService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String value = (String) request.getAttribute("Authorization");
    return accountService.tokenAuthorize(value);
  }
}
