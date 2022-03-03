package alanisia.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WhiteList extends WebMvcConfigurationSupport {
  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    InterceptorRegistration registration = registry.addInterceptor(new RequestInterceptor());
    registration.addPathPatterns("/**");
    List<String> pathPatterns = new ArrayList<>();
    pathPatterns.add("/login");
    pathPatterns.add("/register");
    pathPatterns.add("/captcha");
    pathPatterns.add("/blog/newest");
    pathPatterns.add("/blog/category");
    pathPatterns.add("/blog/search");
    pathPatterns.add("/blog/detail/**");
    pathPatterns.add("/comments");
    pathPatterns.add("/replies");
    registration.excludePathPatterns(pathPatterns);
    super.addInterceptors(registry);
  }
}
