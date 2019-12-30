//package com.king.king.mbg.utli;
//
//import org.apache.commons.beanutils.BeanUtils;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UtliMain2 {
//
//
//    /**
//     * <p>
//     * BeanUtils.copyProperties 工具类演示
//     * </p>
//     */
//    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
//
//        A a = new A();
//        a.setCode("1");
//        a.setName("2");
//        a.setId("3");
//        List<C> listc = new ArrayList<>();
//        C c = new C();
//        c.setCode("listccode");
//        c.setName("listcname");
//        listc.add(c);
//        a.setListc(listc);
//        //  System.out.println(a.getCode() + " " + a.getName() );
//
//
//        B b = new B();
//        BeanUtils.copyProperties(b, a);
//        System.out.println("B对象的code:" + b.getCode() + " B对象的name:" + b.getName());
//        System.out.println("B对象的list:" + b.getListc().get(0).name);
//
//    }
//
//
//}
