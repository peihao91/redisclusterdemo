package com.trinclesdar.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private Jedis jedis;

    @Test
    public void contextLoads() {
        String key = "";
        for (int i = 1;i<6;i++){
            key = "check_"+i;
            String set = jedis.set(key, "ewev"+i, "NX", "EX", 60 + i);
            System.out.println(set);
        }
    }
}
