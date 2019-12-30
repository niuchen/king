package com.king.king.mbg.mybatisGeneratorV2.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * 实体的自定义Lombok生成插件
 * 1.增加@Data注解
 * 2.取消get set方法
 * <p>
 * <p>
 * * PluginAdapter 类中的几乎每个方法都包含 操作方法
 * * *插件接口。客户端可以扩展这个类来实现部分或全部
 * * *插件中的方法。
 * * * < p >
 * * *此适配器没有实现<tt>validate</tt>方法-所有插件
 * * *必须执行验证。
 */
public class CustomLombokPlugin extends PluginAdapter {

    private static final Set<LombokEnum> lombokEnums = new LinkedHashSet<>();

    private boolean builder;

    public CustomLombokPlugin() {
        super();
        builder = false;
        lombokEnums.add(LombokEnum.DATA);
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        builder = Boolean.valueOf(properties.getProperty("builder"));
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /****方法生成基记录类后调用此方法***/
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (builder) {
            lombokEnums.add(LombokEnum.BUILDER);
        }
        lombokEnums.forEach(lombok -> {
            topLevelClass.addImportedType(lombok.importedType);
            topLevelClass.addAnnotation(lombok.annotation);
        });

        return true;
    }

    /***实体生成的get***/
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /***实体生成的set***/
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    enum LombokEnum {
        /**
         * 枚举
         */
        DATA("@Data", "lombok.Data"),
        BUILDER("@Builder", "lombok.Builder"),
        ;
        private final String annotation;
        private final String importedType;

        LombokEnum(String annotation, String importedType) {
            this.annotation = annotation;
            this.importedType = importedType;
        }
    }
}
