package com.king.king.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * description: .
 *
 * @author 赵乔功
 * @date 2019-08-01
 */
public class FileAttributeCodeUtil {
  /**
   * 获得文件状态码
   */
  public static String getFeatureCode(MultipartFile multipartFile){
    String code ="";
    InputStream inputStream =null;
    try {
      inputStream = multipartFile.getInputStream();
      byte[] b = new byte[3];
      inputStream.read(b, 0, b.length);
      code = bytesToHexString(b);
      code = code.toUpperCase();
    }catch (Exception e){
    }finally {
      if(inputStream!=null){
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return code;
  }

    private  static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
