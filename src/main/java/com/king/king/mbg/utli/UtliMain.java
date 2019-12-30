package com.king.king.mbg.utli;

import org.springframework.beans.BeanUtils;

public class UtliMain {


    /**
     * <p>
     * 工具类演示
     * </p>
     */
    public static void main(String[] args) {

        A a = new A();
        a.code = "1";
        a.name = "2";
        a.id = "3";
        a.url = "4";


        System.out.println(a.code + " " + a.name);

        B b = new B();
        BeanUtils.copyProperties(a, b);
        System.out.println(b.code + " " + b.name);


    }

    static class A {
        String name;
        String code;
        String id;
        String url;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class B {
        String name;
        String code;
        String id;
        String url;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
