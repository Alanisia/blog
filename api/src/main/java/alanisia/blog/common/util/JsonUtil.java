package alanisia.blog.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonUtil {
  private static final ObjectMapper mapper = new ObjectMapper();

  public static String json(Object value) {
    try {
      ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
      return writer.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      log.warn("{}", e.getMessage());
      return "";
    }
  }
}
