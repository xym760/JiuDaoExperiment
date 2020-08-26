package com.jiudao.experiment.controller;

import com.jiudao.experiment.entity.Club;
import com.jiudao.experiment.utils.ProtostuffSerializer;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisTestController {

    /**
     * jedis基础数据api
     *
     * @return
     */
    @GetMapping("/redisTest")
    public static String redisTest() {
        Jedis jedis = null;

        try {
            // 生成一个Jedis对象，这个对象负责和指定Redis实例进行通信
            jedis = new Jedis("192.168.181.128", 6379);
            // 1.String
            jedis.set("hello", "world");
            System.out.println(jedis.get("hello"));
            System.out.println(jedis.incr("counter"));
            // 2.hash
            jedis.hset("myhash", "f1", "v1");
            jedis.hset("myhash", "f2", "v2");
            System.out.println(jedis.hgetAll("myhash"));
            // 3.list
            jedis.rpush("mylist", "1");
            jedis.rpush("mylist", "2");
            jedis.rpush("mylist", "3");
            System.out.println(jedis.lrange("mylist", 0, -1));
            // 4.set
            jedis.sadd("myset", "a");
            jedis.sadd("myset", "b");
            jedis.sadd("myset", "a");
            System.out.println(jedis.smembers("myset"));
            // 5.zset
            jedis.zadd("myzset", 99, "tom");
            jedis.zadd("myzset", 66, "peter");
            jedis.zadd("myzset", 33, "james");
            Set<Tuple> myzset = jedis.zrangeWithScores("myzset", 0, -1);
            for (Tuple t : myzset) {
                System.out.println(t.getElement());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {// 关闭连接
                jedis.close();
            }
        }
        return null;
    }

    /**
     * jedis通过字节数组实现对象存取
     */
    public static void redisSerializationTest() {
        Jedis jedis = null;
        try {
            //生成序列化工具类
            ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
            //生成Jedis对象
            jedis = new Jedis("192.168.181.128", 6379);
            //进行序列化
            String key = "club:1";
            //定义实体对象
            Club club = new Club(1, "AC", "米兰", new Date(), 1);
            //序列化
            byte[] clubBytes = protostuffSerializer.serialize(club);
            jedis.set(key.getBytes(), clubBytes);
            //进行反序列化
            byte[] resultBytes = jedis.get(key.getBytes());
            // 反序列化[id=1, clubName=AC, clubInfo=米兰, createDate=Tue Sep 15 09:53:18 CST
            // 2015, rank=1]
            Club resultClub = protostuffSerializer.deserialize(resultBytes);
            System.out.println(resultClub);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Jedis连接池
     */
    public static void connectionPoolTest() {
        //common-pool连接池配置，这里使用默认配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        //初始化Jedis连接池
        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.181.128", 6379);
        Jedis jedis = null;
        try {
            // 1. 从连接池获取jedis对象
            jedis = jedisPool.getResource();
            // 2. 执行操作
            System.out.println(jedis.get("hello"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                // 如果使用JedisPool，close操作不是关闭连接，代表归还连接池
                jedis.close();
            }
        }
    }

    /**
     * Redis中Pipeline的使用方法，模拟mget、mset命令，执行批量删除
     *
     * @param keys
     */
    public static void mdelTest(List<String> keys) {
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.181.128", 6379);
            // 1)生成pipeline对象
            Pipeline pipeline = jedis.pipelined();
            // 2)将del命令封装到pipeline中，注意此时命令并未真正执行
            for (String key : keys) {
                pipeline.del(key);
            }
            pipeline.set("xym", "760");
            // 3)执行命令
            pipeline.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * lua脚本测试
     */
    public static void luaTest() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.181.128", 6379);
            String key = "hello";
            String script = "return redis.call('get',KEYS[1])";
            //1.使用eval方法
//            eval函数有三个参数，分别是：
//            ·script：Lua脚本内容。
//            ·keyCount：键的个数。
//            ·params：相关参数KEYS和ARGV。
            Object result = jedis.eval(script, 1, key);
            System.out.println(result);
            //2.使用scriptLoad和evalsha方式
            //得到脚本的SHA1校验和，
            String scriptSha = jedis.scriptLoad(script);
            result = jedis.evalsha(scriptSha, 1, key);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    public static void test() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.181.128", 6379);
            String key = "hello";
            System.out.println(jedis.get(key));
// 2) 休息31秒
            TimeUnit.SECONDS.sleep(31);
// 3) 执行get操作
            System.out.println(jedis.get(key));
// 4) 休息5秒
            TimeUnit.SECONDS.sleep(5);
// 5) 关闭jedis连接
            jedis.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static void sentinelTest() {
//        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName,
//                sentinelSet, poolConfig, timeout);
        Jedis jedis = null;
        try {
//            jedis = jedisSentinelPool.getResource();
// jedis command
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }

    }

    public static void main(String[] args) {
        test();
    }
}
