package alanisia.blog.common.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public final class CryptoUtil {
  public static String sha256(String plain) {
    Digester digester = new Digester(DigestAlgorithm.SHA256);
    return digester.digestHex(plain);
  }
}
