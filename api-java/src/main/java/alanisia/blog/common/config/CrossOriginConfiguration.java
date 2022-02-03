package alanisia.blog.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

// @EnableWebMvc
// @Configuration
public class CrossOriginConfiguration extends WebMvcConfigurationSupport {
  @Override
  protected void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedMethods("GET" ,"POST", "OPTIONS")
      .allowedOrigins("*").maxAge(3600);
  }
}
