package com.trinclesdar.redis.demo;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DefaultedRedisConnection;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceExceptionConverter;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * @author peihao.
 * @description redis集群监听器
 * @Created 2019/10/17.
 */
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    RedisDistributedLock redisDistributedLock;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Override
    public void onMessage(Message message, byte[] bytes) {
        String mesg = message.toString();
        if (mesg.contains("Lock.")){
            return;
        }
        String key = "Lock."+mesg;
        String value = UUID.randomUUID().toString();
        boolean lock = redisDistributedLock.lock(key, value, 3);
        try {
            if (lock){
                log.info("监听到的失效key为："+mesg);
                redisDistributedLock.unLock(key,value);
            }else {
                log.info("失效key被其他节点监听");
            }
        }catch (Exception e){
            log.error("异常：",e);
        }
    }

    /*@Override
    public void onMessage(String channel, String message) {
        log.info("redis监听到key:"+message);
        String lockKey = "";
        String value = "";

        log.info(LoggerJsonUtil.logJson("check","redis失效key监听","peihao","message","失效key："+message+"监听成功"));
    }*/
}
