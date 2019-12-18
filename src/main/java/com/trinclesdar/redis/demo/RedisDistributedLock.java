package com.trinclesdar.redis.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.concurrent.TimeUnit;

/**
 * @author peihao.
 * @description redis分布式锁
 * @Created 2019/10/15.
 */
@Component
public class RedisDistributedLock {

    @Autowired
    private RedisTemplate redisTemplate;

    /*@Autowired
    private Jedis jedis;*/

    private final static String NX = "NX";
    private final static String EX = "EX";
    private final static String OK = "OK";

    /**
     * 加锁
     * @author peihao
     * @date 2019/10/15
     * @param  key 锁
     * @param  requestId 请求标识 一般为uuid
     * @param  expire 超时时间
     * @return
     */
    public synchronized boolean lock(String key,String requestId,long expire){
        return (Boolean)redisTemplate.execute((RedisCallback) redisConnection -> {
            Boolean result = redisConnection.set(key.getBytes(), requestId.getBytes(), Expiration.from(expire, TimeUnit.SECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT);
            return result;
        });
    }

    /**
     * 解锁
     * @author peihao
     * @date 2019/10/15
     * @param  key 锁
     * @param  requestId 请求标识
     * @return
     */
    public synchronized boolean unLock(String key,String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object execute = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Object nativeConnection = redisConnection.getNativeConnection();
                // 集群模式和单点模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                // 集群
                if (nativeConnection instanceof JedisCluster) {
                    return ((JedisCluster) nativeConnection).eval(script, 1,key,requestId);
                }
                return null;
            }
        });
        return true;
    }

    /**
     * 获取超时时间
     * @author peihao
     * @date 2019/10/15
     * @param  key
     * @return
     */
    public Long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 加锁
     *
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    /*public boolean redisLock(String key, String value,Long time) {
        try {
            String result = jedis.set(key, value, NX, EX, time);
            if ("OK".equals(result)){
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }*/

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    /*public boolean unRedisLock(String key, String value) {
        try {
            String result = jedis.get(key);
            if (StringUtils.isNotEmpty(result) && value.equals(result)){
                jedis.del(key);
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }*/

}
