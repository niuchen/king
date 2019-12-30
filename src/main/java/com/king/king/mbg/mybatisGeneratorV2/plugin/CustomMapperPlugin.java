//package com.king.king.mbg.mybatisGeneratorV2.plugin;
//
//import com.google.common.base.CaseFormat;
//import org.mybatis.generator.api.IntrospectedTable;
//import org.mybatis.generator.api.PluginAdapter;
//import org.mybatis.generator.api.dom.java.*;
//
//import java.util.List;
//
//public class CustomMapperPlugin extends PluginAdapter {
//
//    /**
//     * Statement ID VALUE (findById)
//     */
//    private static final String STATEMENT_FIND_BY_ID = "findById";
//
//    /**
//     * Statement LIST VALUE (findList)
//     */
//    private static final String STATEMENT_FIND_LIST = "findList";
//
//    /**
//     * Statement ID VALUE (insert)
//     */
//    private static final String STATEMENT_INSERT = "insertEntity";
//
//    /**
//     * Statement ID VALUE (update)
//     */
//    private static final String STATEMENT_UPDATE = "updateEntity";
//
//    /**
//     * Statement ID VALUE (delete)
//     */
//    private static final String STATEMENT_DELETE_BY_ID = "deleteById";
//
//
//    @Override
//    public boolean validate(List<String> warnings) {
//        return true;
//    }
//
//    /**
//     * 重命名Mapper接口文件中的selectByPrimaryKey为selectById
//     *
//     * @param method            当前方法
//     * @param interfaze         所属Mapper的java接口
//     * @param introspectedTable 当前数据库表的描述信息
//     * @return
//     */
//    @Override
//    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
//        method.setName(STATEMENT_FIND_BY_ID);
//        //  removeResults(method);
//        return super.clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
//    }
//
//    /**
//     * 重命名Mapper接口文件中的deleteByPrimaryKey为deleteById
//     */
//    @Override
//    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
//        method.setName(STATEMENT_DELETE_BY_ID);
//        return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
//    }
//
//    @Override
//    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
//        method.setName(STATEMENT_INSERT);
//        return super.clientInsertMethodGenerated(method, interfaze, introspectedTable);
//    }
//
//    @Override
//    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
//            Method method, Interface interfaze,
//            IntrospectedTable introspectedTable) {
//        method.setName(STATEMENT_UPDATE);
//        return super.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable);
//    }
//
//    @Override
//    public boolean clientSelectAllMethodGenerated(Method method,
//                                                  Interface interfaze, IntrospectedTable introspectedTable) {
//
//        method.setName(STATEMENT_FIND_LIST);
//        // removeResults(method);
//        return super.clientSelectAllMethodGenerated(method, interfaze, introspectedTable);
//    }
//
//    @Override
//    public boolean clientGenerated(Interface interfaze,
//                                   TopLevelClass topLevelClass,
//                                   IntrospectedTable introspectedTable) {
//        addBatchDelete(interfaze, topLevelClass, introspectedTable);
//        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
//    }
//
//    private void addBatchDelete(Interface interfaze, TopLevelClass topLevelClass,
//                                IntrospectedTable introspectedTable) {
//        Method method = new Method();
//        method.setName("batchDelete");
//        FullyQualifiedJavaType newListInstance = FullyQualifiedJavaType.getNewListInstance();
//        newListInstance.addTypeArgument(new FullyQualifiedJavaType("java.lang.Long"));
//        method.addParameter(new Parameter(newListInstance, "ids"));
//        method.setReturnType(new FullyQualifiedJavaType("java.lang.Long"));
//        String tableName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, introspectedTable.getFullyQualifiedTable().toString());
//        method.addAnnotation("@UpdateProvider(type = " + tableName + "SqlBuilder.class, method = " + tableName + "SqlBuilder.BATCH_DELETE_SQL)");
//        interfaze.addMethod(method);
//        interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.UpdateProvider"));
//    }
//
////    private void removeResults(Method method) {
////        boolean inResultsAnnotation = false;
////        Iterator<String> iter = method.getAnnotations().iterator();
////        while (iter.hasNext()) {
////            String annotation = iter.next();
////            if (inResultsAnnotation) {
////                if ("})".equals(annotation)) {
////                }
////                iter.remove();
////            } else if (annotation.startsWith("@Results(")) {
////                inResultsAnnotation = true;
////                iter.remove();
////            }
////        }
////    }
//
//}
