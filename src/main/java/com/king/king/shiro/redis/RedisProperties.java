//package com.king.king.shiro.redis;
//
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
////redis:
////        host: db.elitescloud.com
////        port: 25007
////        jedis:
////        pool:
////        max-idle: 8
////        min-idle: 0
////        max-active: 8
////        max-wait: -1
////        timeout: 0
////        password: Xmm-5fLqAbELSw9AviWN
////        database: 2
//@Component
//@ConfigurationProperties(prefix = "spring.redis")
//public class RedisProperties {
//    private int expireSeconds;
//    private List<String> clusterNodes = new ArrayList<String>();
//
//    private int commandTimeout;
//    private Map<String, Integer> pool = new HashMap<>();
//    private String password;
//    /*****/
//    private String host;
//    private int  port;
//    private String database;
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public String getDatabase() {
//        return database;
//    }
//
//    public void setDatabase(String database) {
//        this.database = database;
//    }
//
//    public int getExpireSeconds() {
//        return expireSeconds;
//    }
//
//    public void setExpireSeconds(int expireSeconds) {
//        this.expireSeconds = expireSeconds;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getCommandTimeout() {
//        return commandTimeout;
//    }
//
//    public void setCommandTimeout(int commandTimeout) {
//        this.commandTimeout = commandTimeout;
//    }
//
//    public Map<String, Integer> getPool() {
//        return pool;
//    }
//
//    public void setPool(Map<String, Integer> pool) {
//        this.pool = pool;
//    }
//
//    public List<String> getClusterNodes() {
//        return clusterNodes;
//    }
//
//    public void setClusterNodes(List<String> clusterNodes) {
//        this.clusterNodes = clusterNodes;
//    }
//}
