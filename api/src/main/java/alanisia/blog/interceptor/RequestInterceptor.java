package alanisia.blog.interceptor;

import alanisia.blog.common.enums.Result;
import alanisia.blog.exception.BusinessException;
import alanisia.blog.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {
  @Autowired
  private AccountService accountService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
      response.setStatus(HttpServletResponse.SC_OK);
      return true;
    }
    String value = request.getHeader("Authorization");
    log.debug("authorization = {}", value);
    if (value == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      throw new BusinessException(Result.NULL_EXCEPTION);
    }
    if (!accountService.tokenAuthorize(value)) {
      log.debug("auth failed");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      throw new BusinessException(Result.AUTHORIZE_FAILED);
    }
    return true;
  }
}
