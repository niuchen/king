package com.king.king.mbg.mybatisgenerator1;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/***
 * 原始配置启动类
 * niuchen 20191124
 * ***/
public class MybatisGeneratorMain {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
//        File fie= new File("src/main/resources/mybatis-generator.xml");
//        System.out.println(fie.getPath());
//        System.out.println(fie.getCanonicalPath());
//        System.out.println(fie.getAbsolutePath());
        Configuration config = cp.parseConfiguration(new File("src/main/java/com/king/king/mbg/mybatisGeneratorV1/mybatis-generator.xml"));
        // Configuration config = cp.parseConfiguration(ClassLoader.getSystemResourceAsStream("mybatis-generator.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
