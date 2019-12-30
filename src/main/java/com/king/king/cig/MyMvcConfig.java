//package com.king.king.cig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
///**
// * Spring MVC配置
// *
// * @author EGWri
// */
//@Configuration//声明当前类是一个配置类
//@EnableWebMvc//开启默认配置,开启SpringMVC支持
//@ComponentScan("com.king.king")//自动扫描包名下所有使用@Service、@Component、@Repository和@Controller的类，并注册为Bean
//public class MyMvcConfig {
//
////    @Bean
////    public InternalResourceViewResolver viewResolver(){
////        //SpringMVC渲染核心，映射路径和实际页面的位置
////        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
////        viewResolver.setPrefix("/WEB-INF/classes/views/");//表示路径前缀（运行时目录结构）
////        viewResolver.setSuffix(".jsp");//表示路径后缀，假设 ViewName 为 hello，则完整路径为 /WEB-INF/page/hello.jsp
////        viewResolver.setViewClass(JstlView.class);//viewClass： 表示要解析的视图类型
////        return viewResolver;
////    }
//}
