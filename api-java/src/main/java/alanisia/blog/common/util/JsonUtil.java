package alanisia.blog.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public final class JsonUtil {
  private static final ObjectMapper mapper = new ObjectMapper();

  public static String json(Object value) throws JsonProcessingException {
    ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
    return writer.writeValueAsString(value);
  }
}
