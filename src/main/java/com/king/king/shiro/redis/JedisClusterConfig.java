//package com.king.king.shiro.redis;
//
//
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Configuration
//public class JedisClusterConfig {
//
//    @Autowired
//    privateRedisProperties redisProperties;
//
//    /**
//     * 返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
//     * @return
//     */
//    @Bean
//    public JedisCluster getJedisCluster() {
//
//        Set<HostAndPort> nodes = new HashSet<>();
//
//        List<String> clusterNodes = redisProperties.getClusterNodes();
//        for (String ipPort : clusterNodes) {
//            String[] ipPortPair = ipPort.split(":");
//            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
//        }
//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        poolConfig.setMaxTotal(redisProperties.getPool().get("maxActive"));//最大连接数
//        poolConfig.setMaxIdle(redisProperties.getPool().get("maxIdle"));//最大空闲连接数
//        poolConfig.setMinIdle(redisProperties.getPool().get("minIdle"));//最小空闲连接数
//        poolConfig.setMaxWaitMillis(redisProperties.getPool().get("maxWait").longValue());//连接最大等待时间
//        return new JedisCluster(nodes,redisProperties.getCommandTimeout(),redisProperties.getCommandTimeout(),
//                redisProperties.getPool().get("maxAttempts"),redisProperties.getPassword() ,poolConfig);//需要密码连接的创建对象方式
//
//    }
//}
