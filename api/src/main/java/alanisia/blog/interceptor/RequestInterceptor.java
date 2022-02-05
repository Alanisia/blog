package alanisia.blog.interceptor;

import alanisia.blog.common.enums.Result;
import alanisia.blog.exception.BusinessException;
import alanisia.blog.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
  @Autowired private AccountService accountService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String value = (String) request.getAttribute("Authorization");
    log.debug("Authorization = {}", value);
    if (!accountService.tokenAuthorize(value)) {
      log.debug("Auth failed");
      throw new BusinessException(Result.AUTHORIZE_FAILED);
    }
    return true;
  }
}
