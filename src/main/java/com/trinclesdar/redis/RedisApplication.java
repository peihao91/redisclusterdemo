package com.trinclesdar.redis;

import com.cinderella.framework.common.security.annotation.EnableCinderellaFeignClients;
import com.cinderella.framework.common.swagger.annotation.EnableCinderellaSwagger2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

@SpringBootApplication
@EnableCinderellaSwagger2
@SpringCloudApplication
@EnableCinderellaFeignClients(basePackages = {"com.cinderella", "com.galaxydc"})
@EnableScheduling
public class RedisApplication implements ApplicationRunner{

    @Resource(name = "jedisClusters")
    private JedisCluster jedisCluster;

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*jedisCluster.set("120", "1231321", "NX", "EX", 20);
        jedisCluster.set("121", "1231321", "NX", "EX", 22);
        jedisCluster.set("122", "1231321", "NX", "EX", 24);
        jedisCluster.set("123", "1231321", "NX", "EX", 26);
        jedisCluster.set("124", "1231321", "NX", "EX", 28);
        jedisCluster.set("125", "1231321", "NX", "EX", 30);
        jedisCluster.set("126", "1231321", "NX", "EX", 32);
        jedisCluster.set("127", "1231321", "NX", "EX", 34);
        jedisCluster.set("128", "1231321", "NX", "EX", 36);
        jedisCluster.set("129", "1231321", "NX", "EX", 38);
        jedisCluster.set("130", "1231321", "NX", "EX", 40);
        jedisCluster.set("131", "1231321", "NX", "EX", 42);
        jedisCluster.set("132", "1231321", "NX", "EX", 44);
        jedisCluster.set("133", "1231321", "NX", "EX", 46);
        jedisCluster.set("134", "1231321", "NX", "EX", 48);
        jedisCluster.set("136", "1231321", "NX", "EX", 50);
        jedisCluster.set("137", "1231321", "NX", "EX", 52);
        jedisCluster.set("138", "1231321", "NX", "EX", 54);
        jedisCluster.set("139", "1231321", "NX", "EX", 56);
        jedisCluster.set("140", "1231321", "NX", "EX", 58);
        jedisCluster.set("141", "1231321", "NX", "EX", 60);
        jedisCluster.set("142", "1231321", "NX", "EX", 62);
        jedisCluster.set("143", "1231321", "NX", "EX", 64);
        jedisCluster.set("144", "1231321", "NX", "EX", 66);
        jedisCluster.set("146", "1231321", "NX", "EX", 68);
        jedisCluster.set("147", "1231321", "NX", "EX", 70);
        jedisCluster.set("148", "1231321", "NX", "EX", 72);
        jedisCluster.set("149", "1231321", "NX", "EX", 74);*/
    }
}
