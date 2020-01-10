package com.king.king.util;

import java.lang.annotation.*;

/**
 * @author Danfeng
 * @since 2018/1/12.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface ExcelField {
    /**
     * 表头
     * @return
     */
    String title();

    /**
     * 是否显示
     * @return
     */
    boolean isShow() default true;

    /**
     * 是否必须
     * @return
     */
    boolean isRequired() default true;

    /**
     * 导出字段格式   金额  日期  百分比需要特殊处理
     * */
    ExcleFieldTypeEnum fieldtype() default ExcleFieldTypeEnum.DEFAULT;
}
