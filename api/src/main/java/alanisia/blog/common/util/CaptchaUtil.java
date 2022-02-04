package alanisia.blog.common.util;

import cn.hutool.captcha.CircleCaptcha;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

public final class CaptchaUtil {
  @Data
  @Accessors(chain = true)
  public static class Captcha implements Serializable {
    private String code;
    private String base64;
  }

  public static Captcha generate(int width, int height) {
    CircleCaptcha captcha = cn.hutool.captcha.CaptchaUtil.createCircleCaptcha(width, height);
    return new Captcha().setBase64(captcha.getImageBase64()).setCode(captcha.getCode());
  }
}
