package com.king.king.mbg.mybatisGeneratorV2.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * Swagger2 实体注入插件
 * <p>
 * PluginAdapter 类中的几乎每个方法都包含 操作方法
 * *插件接口。客户端可以扩展这个类来实现部分或全部
 * *插件中的方法。
 * * < p >
 * *此适配器没有实现<tt>validate</tt>方法-所有插件
 * *必须执行验证。
 */
public class CustomSwagger2Plugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /***此方法在为特定列生成字段后调用  对这个实体类进行增强 增加swagger注解***/
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String classAnnotation = "@ApiModel(value=\"" + introspectedTable.getRemarks() + "\")";
        if (!topLevelClass.getAnnotations().contains(classAnnotation)) {
            topLevelClass.addAnnotation(classAnnotation);
        }

        String apiModelAnnotationPackage = properties.getProperty("apiModelAnnotationPackage");
        String apiModelPropertyAnnotationPackage = properties.getProperty("apiModelPropertyAnnotationPackage");
        if (null == apiModelAnnotationPackage) {
            apiModelAnnotationPackage = "io.swagger.annotations.ApiModel";
        }
        if (null == apiModelPropertyAnnotationPackage) {
            apiModelPropertyAnnotationPackage = "io.swagger.annotations.ApiModelProperty";
        }

        topLevelClass.addImportedType(apiModelAnnotationPackage);
        topLevelClass.addImportedType(apiModelPropertyAnnotationPackage);

        field.addAnnotation("@ApiModelProperty(value=\"" + introspectedColumn.getRemarks() + "\")");
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }
}
