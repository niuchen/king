//package com.king.king.mbg.mybatisGeneratorV2.plugin;
//
//import com.google.common.base.CaseFormat;
//import freemarker.cache.ClassTemplateLoader;
//import freemarker.cache.NullCacheStorage;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import freemarker.template.TemplateExceptionHandler;
//import org.mybatis.generator.api.IntrospectedTable;
//import org.mybatis.generator.api.PluginAdapter;
//import org.mybatis.generator.api.dom.java.TopLevelClass;
//import org.springframework.util.StringUtils;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//
//
///**
// * 生成api service层类
// *
// * @author Danfeng
// * @since 2019/1/28
// */
//public class CustomBasePlugin extends PluginAdapter {
//
//    private Properties systemPro = System.getProperties();
//
//    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);
//
//    static {
//        CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(CustomBasePlugin.class, "/templates"));
//        CONFIGURATION.setDefaultEncoding("UTF-8");
//        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
//    }
//
//    private Template getTemplate(String templateName) throws IOException {
//        try {
//            return CONFIGURATION.getTemplate(templateName);
//        } catch (IOException e) {
//            throw e;
//        }
//    }
//
//
//    @Override
//    public boolean validate(List<String> warnings) {
//        return true;
//    }
//
//    /**
//     * 修改实体类
//     *
//     * @param topLevelClass
//     * @param introspectedTable
//     * @return
//     */
//    @Override
//    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        try {
//            // 生成controller文件
//            generateControllerFile(topLevelClass, introspectedTable);
//            // 生成service文件
//            generateServiceFile(topLevelClass, introspectedTable);
//            // 生成Impl文件
//            generateImplFile(topLevelClass, introspectedTable);
//            // 生成sql
//            generateSqlBuilderFile(topLevelClass, introspectedTable);
//            generateSqlCriteriaFile(topLevelClass, introspectedTable);
//        } catch (IOException | TemplateException e) {
//            e.printStackTrace();
//        }
//        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
//    }
//
//    private void generateControllerFile(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) throws IOException, TemplateException {
//        String tableUmp = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, introspectedTable.getFullyQualifiedTable().toString());
//        String path = properties.getProperty("apiTargetProject") + "/" + properties.getProperty("apiTargetPackage").replace(".", "/");
//        File catalog = new File(path);
//        boolean mkdirs = catalog.mkdirs();
//        File mapperFile = new File(path + '/' + tableUmp + "Api.java");
//        Template template = getTemplate("Api.ftl");
//        FileOutputStream fos = new FileOutputStream(mapperFile);
//        Writer out = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8), 10240);
//        Map<String, Object> dataMap = new HashMap<>(4);
//        dataMap.put("apiTargetPackage", properties.getProperty("apiTargetPackage"));
//        dataMap.put("profile", properties.getProperty("profile"));
//        dataMap.put("author", systemPro.getProperty("user.name"));
//        dataMap.put("tableRemark", introspectedTable.getRemarks());
//        dataMap.put("entityName", tableUmp);
//        dataMap.put("date", new Date());
//        template.process(dataMap, out);
//    }
//
//    private void generateServiceFile(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) throws IOException, TemplateException {
//        String fileName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, introspectedTable.getFullyQualifiedTable().toString());
//        String path = properties.getProperty("serviceTargetProject") + "/" + properties.getProperty("serviceTargetPackage").replace(".", "/");
//        File catalog = new File(path);
//        boolean mkdirs = catalog.mkdirs();
//        File mapperFile = new File(path + '/' + fileName + "Service.java");
//        Template template = getTemplate("Service.ftl");
//        FileOutputStream fos = new FileOutputStream(mapperFile);
//        Writer out = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8), 10240);
//        Map<String, Object> dataMap = new HashMap<>(4);
//        dataMap.put("serviceTargetPackage", properties.getProperty("serviceTargetPackage"));
//        dataMap.put("author", systemPro.getProperty("user.name"));
//        dataMap.put("tableRemark", introspectedTable.getRemarks());
//        dataMap.put("entityName", fileName);
//        dataMap.put("date", new Date());
//        template.process(dataMap, out);
//    }
//
//    private void generateImplFile(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) throws IOException, TemplateException {
//        String fileName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, introspectedTable.getFullyQualifiedTable().toString());
//        String path = properties.getProperty("serviceTargetProject") + "/" + properties.getProperty("serviceTargetPackage").replace(".", "/");
//        File catalog = new File(path);
//        boolean mkdirs = catalog.mkdirs();
//        File mapperFile = new File(path + '/' + fileName + "ServiceImpl.java");
//        Template template = getTemplate("ServiceImpl.ftl");
//        FileOutputStream fos = new FileOutputStream(mapperFile);
//        Writer out = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8), 10240);
//        Map<String, Object> dataMap = new HashMap<>(4);
//        dataMap.put("serviceTargetPackage", properties.getProperty("serviceTargetPackage"));
//        dataMap.put("profile", properties.getProperty("profile"));
//        dataMap.put("author", systemPro.getProperty("user.name"));
//        dataMap.put("tableRemark", introspectedTable.getRemarks());
//        dataMap.put("entityName", fileName);
//        dataMap.put("date", new Date());
//        template.process(dataMap, out);
//    }
//
//    private void generateSqlBuilderFile(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) throws IOException, TemplateException {
//        String sqlTargetProject = properties.getProperty("sqlTargetProject");
//        String sqlTargetPackage = properties.getProperty("sqlTargetPackage");
//        if (StringUtils.hasText(sqlTargetPackage) && StringUtils.hasText(sqlTargetProject)) {
//            String fileName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, introspectedTable.getFullyQualifiedTable().toString());
//            String path = sqlTargetProject + "/" + sqlTargetPackage.replace(".", "/");
//            File catalog = new File(path);
//            boolean mkdirs = catalog.mkdirs();
//            File mapperFile = new File(path + '/' + fileName + "SqlBuilder.java");
//            Template template = getTemplate("SqlBuilder.ftl");
//            FileOutputStream fos = new FileOutputStream(mapperFile);
//            Writer out = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8), 10240);
//            Map<String, Object> dataMap = new HashMap<>(5);
//            dataMap.put("sqlTargetPackage", sqlTargetPackage);
//            dataMap.put("author", systemPro.getProperty("user.name"));
//            dataMap.put("tableRemark", introspectedTable.getRemarks());
//            dataMap.put("entityName", fileName);
//            dataMap.put("tableName", introspectedTable.getFullyQualifiedTable().toString());
//            dataMap.put("date", new Date());
//            template.process(dataMap, out);
//        }
//    }
//
//    private void generateSqlCriteriaFile(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) throws IOException, TemplateException {
//        String sqlTargetProject = properties.getProperty("sqlTargetProject");
//        String sqlTargetPackage = properties.getProperty("sqlTargetPackage");
//        if (StringUtils.hasText(sqlTargetPackage) && StringUtils.hasText(sqlTargetProject)) {
//            String fileName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, introspectedTable.getFullyQualifiedTable().toString());
//            String path = sqlTargetProject + "/" + sqlTargetPackage.replace(".", "/");
//            File catalog = new File(path);
//            boolean mkdirs = catalog.mkdirs();
//            File mapperFile = new File(path + '/' + fileName + "Criteria.java");
//            Template template = getTemplate("SqlCriteria.ftl");
//            FileOutputStream fos = new FileOutputStream(mapperFile);
//            Writer out = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8), 10240);
//            Map<String, Object> dataMap = new HashMap<>(5);
//            dataMap.put("sqlTargetPackage", sqlTargetPackage);
//            dataMap.put("author", systemPro.getProperty("user.name"));
//            dataMap.put("tableRemark", introspectedTable.getRemarks());
//            dataMap.put("entityName", fileName);
//            dataMap.put("date", new Date());
//            template.process(dataMap, out);
//        }
//    }
//
//}
//
