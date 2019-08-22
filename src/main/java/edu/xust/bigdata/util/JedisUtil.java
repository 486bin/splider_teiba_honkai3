package edu.xust.bigdata.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Properties;

public class JedisUtil {
    private static JedisPool jedisPool;

    static {
        Properties prop = new Properties();
        try {
            prop.load(JedisPool.class.getClassLoader().getResourceAsStream("redis.properties"));
            jedisPool = new JedisPool((String) prop.get("host"), Integer.valueOf(prop.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Jedis getJedisConn(){
        return jedisPool.getResource();
    }
}
