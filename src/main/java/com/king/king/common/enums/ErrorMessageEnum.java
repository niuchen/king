package com.king.king.common.enums;

/**
 *
 * Auther: niuchen
 * Date: 2019/4/22/022 10:57
 * Description: 唐宫NGmessga的信息枚举 统一管理
 * defaultText 是默认文本
 * key扩展使用 目前没有用途可以所以配置.  未来如果国际化 存储唯一标识 用于转换成国际化 文本
 */
public enum ErrorMessageEnum {
    NG_EXPORT_EXCEL("导出异常","1"),

    NG_PO_ITEM("采购单没有找到需要退货商品:ID:","1"),
    NG_ITEM_NOTSUPP("没有找到供应商,商品名称:","1"),
    NG_RO_VERIFICATION1("已收数小于等于已退数,无法退货","1"),
    NG_RO_VERIFICATION2("退货数大于可退数量","1"),
    NG_RO_VERIFICATION3("退货数大于库存数,库存不足","1"),
    ITEM_CODE("商品编号:","1");
    private String key;
    private String defaultText;
     private ErrorMessageEnum(String defaultText, String  key) {
         this.defaultText=defaultText;
         this.key=key;
    }

    public String getDefaultText() {
        return defaultText;
    }
}
