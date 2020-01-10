package com.king.king.common.enums;

/**
 * description:文件特征码 .
 *
 * @author 赵乔功
 * @date 2019-08-01
 */
public enum FileAttributeCode {

    JPG("FFD8FF"),
    PNG("89504E");

    private String featureCode;

    public String getFeatureCode(){
      return featureCode;
    }

    FileAttributeCode(String featureCode){
      this.featureCode=featureCode;
    }

}
