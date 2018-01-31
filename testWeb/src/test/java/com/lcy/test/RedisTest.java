package com.lcy.test;

import com.lcy.common.utils.RedisTemplateUtil;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testRedisForString(){
        redisTemplate.delete("testName`");
        redisTemplate.opsForValue().set("testName","hello");
        Object testName = redisTemplate.opsForValue().get("testName");
        System.out.println("获取结果：testName:"+testName);
    }
    @Test
    public void testRedisForList(){
        redisTemplate.delete("testList");
        redisTemplate.opsForList().rightPush("testList","a");
        redisTemplate.opsForList().rightPush("testList","b");
        redisTemplate.opsForList().leftPush("testList","c");
        List testList = redisTemplate.opsForList().range("testList", 0, -1);
        for (Object str:testList) {
            System.out.print(str+",");
        }
    }
    @Test
    public void testRedisForObj(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        redisTemplate.delete("obj");
        redisTemplate.opsForValue().set("obj",list);
        List testList = (List) redisTemplate.opsForValue().get("obj");
        System.out.println("结果,list:"+list);
    }

    @Test
    public void testUtil(){
        RedisTemplateUtil redis = new RedisTemplateUtil(redisTemplate);
        redis.set("testName","hello man");
        Object testName = redis.get("testName");
        System.out.println("testName:"+testName);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","mark");
        map.put("age","12");
        redis.setMap("testMap",map);
        Map<String,Object> testMap = (Map<String, Object>) redis.getMap("testMap");
        System.out.println("testMap:"+testMap);
    }

    public static void main(String[] args) {
        try {
            Jedis jedis = new Jedis("192.168.188.41",6379);
            jedis.auth("java123");//设置密码
            System.out.println("server is running"+jedis.ping());
            
            jedis.del("testName");
            jedis.set("testName","mark");
            String testName = jedis.get("testName");
            System.out.println("testName:"+testName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
