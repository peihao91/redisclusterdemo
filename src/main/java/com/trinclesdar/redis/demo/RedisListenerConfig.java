package com.trinclesdar.redis.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author peihao.
 * @description redis配置
 * @Created 2019/10/17.
 */
@Slf4j
@Configuration
public class RedisListenerConfig {

    @Value("${spring.redis.sub.clusterAddress}")
    private String redisSubURI;

    @Value("${spring.redis.password}")
    private String redisPassword;

    private List<RedisNode> redisNodes;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    @Bean(name = "jedisClusters")
    JedisCluster jedisCluster(){
        RedisConnectionFactory requiredConnectionFactory = redisTemplate.getRequiredConnectionFactory();
        RedisConnection connection = requiredConnectionFactory.getConnection();
        Object nativeConnection = connection.getNativeConnection();
        JedisCluster jedisCluster = (JedisCluster) nativeConnection;
        return jedisCluster;
    }

    List<RedisNode> getRedisNodes(){
        RedisClusterConfiguration clusterConfiguration = jedisConnectionFactory.getClusterConfiguration();
        Set<RedisNode> clusterNodes = clusterConfiguration.getClusterNodes();
        List<RedisNode> redisNodes = new ArrayList<>(clusterNodes);
        return redisNodes;
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        jedisConnectionFactory.setHostName(getRedisNodes().get(0).getHost());
        jedisConnectionFactory.setPort(getRedisNodes().get(0).getPort());
        jedisConnectionFactory.afterPropertiesSet();
        container.setConnectionFactory(jedisConnectionFactory);
        log.info("host:"+getRedisNodes().get(0).getHost()+"  port:"+getRedisNodes().get(0).getPort());
        return container;
    }

    @Bean
    RedisMessageListenerContainer redisContainer2() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        jedisConnectionFactory.setHostName(getRedisNodes().get(2).getHost());
        jedisConnectionFactory.setPort(getRedisNodes().get(2).getPort());
        jedisConnectionFactory.afterPropertiesSet();
        container.setConnectionFactory(jedisConnectionFactory);
        log.info("host:"+getRedisNodes().get(2).getHost()+"  port:"+getRedisNodes().get(2).getPort());
        return container;
    }


    @Bean
    RedisKeyExpirationListener redisKeyExpirationListener() {
        return new RedisKeyExpirationListener(redisContainer());
    }
    @Bean
    RedisKeyExpirationListener redisKeyExpirationListener2() {
        return new RedisKeyExpirationListener(redisContainer2());
    }
    @Bean
    RedisKeyExpirationListener redisKeyExpirationListener4() {
        return new RedisKeyExpirationListener(redisContainer4());
    }
    /*@Bean
    RedisMessageListenerContainer redisContainer1() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        jedisConnectionFactory.setHostName(getRedisNodes().get(1).getHost());
        jedisConnectionFactory.setPort(getRedisNodes().get(1).getPort());
        jedisConnectionFactory.afterPropertiesSet();
        container.setConnectionFactory(jedisConnectionFactory);
        log.info("host:"+getRedisNodes().get(1).getHost()+"  port:"+getRedisNodes().get(1).getPort());
        return container;
    }*/



    /*@Bean
    RedisMessageListenerContainer redisContainer3() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        jedisConnectionFactory.setHostName(getRedisNodes().get(3).getHost());
        jedisConnectionFactory.setPort(getRedisNodes().get(3).getPort());
        jedisConnectionFactory.afterPropertiesSet();
        container.setConnectionFactory(jedisConnectionFactory);
        log.info("host:"+getRedisNodes().get(3).getHost()+"  port:"+getRedisNodes().get(3).getPort());
        return container;
    }*/

    @Bean
    RedisMessageListenerContainer redisContainer4() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        jedisConnectionFactory.setHostName(getRedisNodes().get(4).getHost());
        jedisConnectionFactory.setPort(getRedisNodes().get(4).getPort());
        jedisConnectionFactory.afterPropertiesSet();
        container.setConnectionFactory(jedisConnectionFactory);
        log.info("host:"+getRedisNodes().get(4).getHost()+"  port:"+getRedisNodes().get(4).getPort());
        return container;
    }

    /*@Bean
    RedisMessageListenerContainer redisContainer5() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        jedisConnectionFactory.setHostName(getRedisNodes().get(5).getHost());
        jedisConnectionFactory.setPort(getRedisNodes().get(5).getPort());
        jedisConnectionFactory.afterPropertiesSet();
        container.setConnectionFactory(jedisConnectionFactory);
        log.info("host:"+getRedisNodes().get(5).getHost()+"  port:"+getRedisNodes().get(5).getPort());
        return container;
    }*/




    /*@Bean
    RedisKeyExpirationListener redisKeyExpirationListener1() {
        return new RedisKeyExpirationListener(redisContainer1());
    }*/



    /*@Bean
    RedisKeyExpirationListener redisKeyExpirationListener3() {
        return new RedisKeyExpirationListener(redisContainer3());
    }*/



    /*@Bean
    RedisKeyExpirationListener redisKeyExpirationListener5() {
        return new RedisKeyExpirationListener(redisContainer5());
    }*/

    /*@Resource(name = "messageListener")
    private RedisKeyExpirationListener messageListener;*/

    /*@Autowired
    private RedisTemplate redisTemplate;*/

    /*@Bean
    RedisMessageListenerContainer container(MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        container.addMessageListener(listenerAdapter, new PatternTopic("__keyevent@0__:expired"));
        return container;
    }*/
    /*@Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(messageListener);
    }*/

    /*@Bean
    RedisClusterConfiguration redisContainer(JedisConnectionFactory connectionFactory) {
        return connectionFactory.getClusterConfiguration();
        *//*Set<RedisNode> clusterNodes = clusterConfiguration.getClusterNodes();
        List<RedisNode> redisNodes = new ArrayList<>(clusterNodes);
        connectionFactory.setHostName(redisNodes.get(0).getHost());
        connectionFactory.setPort(redisNodes.get(0).getPort());
        container.setConnectionFactory(connectionFactory);
        return container;*//*
    }*/

    /*@Bean
    RedisMessageListenerContainer redisContainer(JedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        RedisClusterConfiguration clusterConfiguration = connectionFactory.getClusterConfiguration();
        Set<RedisNode> clusterNodes = clusterConfiguration.getClusterNodes();
        List<RedisNode> redisNodes = new ArrayList<>(clusterNodes);
        connectionFactory.setHostName(redisNodes.get(0).getHost());
        connectionFactory.setPort(redisNodes.get(0).getPort());
        container.setConnectionFactory(connectionFactory);
        return container;
    }*/

    /*@Bean
    RedisKeyExpirationListener redisClusterListener(RedisMessageListenerContainer listenerContainer){
        return new RedisKeyExpirationListener(listenerContainer);
    }*/


}
