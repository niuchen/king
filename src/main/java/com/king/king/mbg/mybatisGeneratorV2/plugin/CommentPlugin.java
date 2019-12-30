package com.king.king.mbg.mybatisGeneratorV2.plugin;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.time.LocalDate;
import java.util.Properties;

/**
 * 自定义注释插件
 * <p>
 * 重写MBG的注释生成类的方法来实现自定义注释
 */
public class CommentPlugin extends DefaultCommentGenerator {
    private Properties systemPro;

//    public CustomCommentPlugin() {
//        super();
//        systemPro = System.getProperties();
//    }

    /*****重写类注释****/
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");

        String remarks = introspectedTable.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                topLevelClass.addJavaDocLine(" * " + remarkLine);
            }
        }
        topLevelClass.addJavaDocLine(" * ");
        StringBuilder sb = new StringBuilder();
        topLevelClass.addJavaDocLine(" * @author  niuchen");
        topLevelClass.addJavaDocLine(" * @since " + getDateString());
        sb.append(" * ").append(introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(sb.toString());
        topLevelClass.addJavaDocLine(" */");
    }

    /*****重写字段注释****/
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");
        String remarks = introspectedColumn.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * " + remarkLine);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        field.addJavaDocLine(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable().toString().toUpperCase());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName().toUpperCase());
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
        // field.addAnnotation("@ApiModelProperty(\""+remarks+"\")");


    }

    @Override
    protected String getDateString() {
        return LocalDate.now().toString();
    }

    /****取消无用的通用注释**/
    @Override
    public void addGeneralMethodComment(Method method,
                                        IntrospectedTable introspectedTable) {
    }

}
