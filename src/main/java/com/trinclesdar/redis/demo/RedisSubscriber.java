package com.trinclesdar.redis.demo;

import com.cinderella.framework.common.core.util.LoggerJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

/**
 * @author peihao.
 * @description 订阅服务
 * @Created 2019/10/17.
 */
@Component
@Slf4j
public class RedisSubscriber /*implements ApplicationRunner */{

    private static final String EXPIRED_CHANNEL = "__keyevent@0__:expired";

    @Value("${spring.redis.sub.clusterAddress}")
    private String redisSubURI;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.cluster.nodes}")
    private String hostAndPort;

    @Value("${spring.redis.pool.maxActive}")
    private Integer maxActive;

    @Value("${spring.redis.pool.maxIdle}")
    private Integer maxIdle;

    @Value("${spring.redis.pool.minIdle}")
    private Integer minIdle;

    @Value("${spring.redis.pool.maxWait}")
    private Long maxWait;


    /*@Autowired
    private RedisKeyExpirationListener redisClusterListener;


    @Autowired
    private Jedis jedis;*/

    /*private RedisNode redisNode(int i){
        RedisClusterConfiguration clusterConfiguration = jedisConnectionFactory.getClusterConfiguration();
        RedisConnection connection = jedisConnectionFactory.getClusterConnection();
        Set<RedisNode> clusterNodes = clusterConfiguration.getClusterNodes();
        List<RedisNode> redisNodes = new ArrayList<>(clusterNodes);
        return redisNodes.get(i);
    }
    *//**
     * 启动监听
     * @author peihao
     * @date 2019/10/28
     * @return
     *//*

    public void startListener0(){
        RedisNode redisNode = redisNode(0);
        Jedis jedis1 = new Jedis(redisNode.getHost(),redisNode.getPort());
        jedis1.set
        jedis1.auth(redisPassword);
        jedis1.subscribe(redisClusterListener,EXPIRED_CHANNEL);
    }

    public void startListener1(){
        RedisNode redisNode = redisNode(1);
        Jedis jedis1 = new Jedis(redisNode.getHost(),redisNode.getPort());
        jedis1.auth(redisPassword);
        jedis1.subscribe(redisClusterListener,EXPIRED_CHANNEL);
    }

    public void startListener2(){
        RedisNode redisNode = redisNode(2);
        Jedis jedis1 = new Jedis(redisNode.getHost(),redisNode.getPort());
        jedis1.auth(redisPassword);
        jedis1.subscribe(redisClusterListener,EXPIRED_CHANNEL);
        *//*List<HostAndPort> hostAndPorts = Arrays.asList(hostAndPort.split(",")).stream().map(s -> {
            String[] split = s.split(":");
            return new HostAndPort(split[0], Integer.valueOf(split[1]));
        }).collect(Collectors.toList());
        for (int i =0;i<6;i++){
            Jedis jedis1 = new Jedis("redis://192.168.40.207:8001"+hostAndPorts.get(0).getHost()+":");
            jedis1.auth(redisPassword);
            jedis1.subscribe(redisClusterListener,EXPIRED_CHANNEL);
        }*//*
        //this.hostAndPorts = hostAndPorts;
        *//*JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxActive);
        config.setMaxWaitMillis(maxWait);
        config.setMinIdle(minIdle);
        JedisCluster jedisCluster = new JedisCluster(hostAndPorts,10000,10000,5,redisPassword,config);
        jedisCluster.subscribe(redisClusterListener,EXPIRED_CHANNEL);*//*
*//*
        JedisCluster jedisCluster = new JedisCluster();*//*
    }

    public void startListener3(){
        RedisNode redisNode = redisNode(3);
        Jedis jedis1 = new Jedis(redisNode.getHost(),redisNode.getPort());
        jedis1.auth(redisPassword);
        jedis1.subscribe(redisClusterListener,EXPIRED_CHANNEL);
    }

    public void startListener4(){
        RedisNode redisNode = redisNode(4);
        Jedis jedis1 = new Jedis(redisNode.getHost(),redisNode.getPort());
        jedis1.auth(redisPassword);
        jedis1.subscribe(redisClusterListener,EXPIRED_CHANNEL);
    }*/

    /*public void startListener5(){
        //RedisNode redisNode = redisNode(5);
        jedis.subscribe(redisClusterListener,EXPIRED_CHANNEL);
    }*/



    /**
     * 关闭监听
     * @author peihao
     * @date 2019/10/28
     * @return
     */

   /* @Override
    public void run(ApplicationArguments args) throws Exception {
        *//*startListener0();
        startListener1();
        startListener2();
        startListener3();
        startListener4();*//*
        startListener5();
        log.info(LoggerJsonUtil.logJson("check","redis监听","peihao","","redis监听初始化"));
    }*/
}
