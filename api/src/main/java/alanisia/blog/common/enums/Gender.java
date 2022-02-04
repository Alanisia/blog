package alanisia.blog.common.enums;

import lombok.Getter;

@Getter
public enum Gender {
  MALE("male", "男"), FEMALE("female", "女");

  private final String en, zh;

  Gender(String en, String zh) {
    this.en = en;
    this.zh = zh;
  }
}
