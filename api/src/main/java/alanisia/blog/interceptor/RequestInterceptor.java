package alanisia.blog.interceptor;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.result.R;
import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
  @Autowired private AccountService accountService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
    if (HttpMethod.OPTIONS.toString().equals(request.getMethod())){
      response.setStatus(HttpServletResponse.SC_OK);
      return true;
    }
    String value = request.getHeader("Authorization");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/json; charset=utf-8");
    PrintWriter writer = response.getWriter();
    log.debug("authorization = {}", value);
    if (value == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      writer.print(JsonUtil.json(R.error(Result.NULL_EXCEPTION)));
      return false;
    }
    if (!accountService.tokenAuthorize(value)) {
      log.debug("auth failed");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      writer.print(JsonUtil.json(R.error(Result.AUTHORIZE_FAILED)));
      return false;
    }
    return true;
  }
}
