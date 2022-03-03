package alanisia.blog.interceptor;

import alanisia.blog.common.enums.Result;
import alanisia.blog.common.result.R;
import alanisia.blog.common.util.JsonUtil;
import alanisia.blog.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
  @Autowired private AccountService accountService;

  // TODO: value is null when the uri of request isn't in white list
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
    String value = request.getHeader("Authorization");
    PrintWriter writer = response.getWriter();
    log.debug("authorization = {}", value);
    if (value == null) {
      // response.sendError(Result.NULL_EXCEPTION.getCode());
      // outputStream.print();
      writer.write(JsonUtil.json(R.error(Result.NULL_EXCEPTION)));
      return false;
    }
      // throw new BusinessException(Result.NULL_EXCEPTION);
    if (!accountService.tokenAuthorize(value)) {
      log.debug("auth failed");
      // throw new BusinessException(Result.AUTHORIZE_FAILED);
      // response.sendError(Result.AUTHORIZE_FAILED.getCode());
      writer.write(JsonUtil.json(R.error(Result.AUTHORIZE_FAILED)));
      return false;
    }
    return true;
  }
}
