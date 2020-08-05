package com.fh.sqh.common;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisUse {


    public static void set(String key,String value){
        Jedis jedis = RedisUtils.getJedis();
        jedis.set(key,value);
        RedisUtils.returnJedis(jedis);
    }

    public static void set(String key,String value,int seconds){
        Jedis jedis = RedisUtils.getJedis();
        jedis.setex(key,seconds,value);
        RedisUtils.returnJedis(jedis);
    }

    public static String get(String key){
        Jedis jedis = RedisUtils.getJedis();
        String value=jedis.get(key);
        RedisUtils.returnJedis(jedis);
        return value;
    }

    public static void hset(String key, String filed, String value){
        Jedis jedis = RedisUtils.getJedis();
        Long hset = jedis.hset(key, filed, value);
        RedisUtils.returnJedis(jedis);
    }

    public static String hget(String key,String filed){
        Jedis jedis = RedisUtils.getJedis();
        String hget = jedis.hget(key, filed);
        RedisUtils.returnJedis(jedis);
        return hget;
    }

    public static long hlen(String key){
        Jedis jedis = RedisUtils.getJedis();
        Long hlen = jedis.hlen(key);
        RedisUtils.returnJedis(jedis);
        return hlen;
    }

    public static List<String> hvals(String key){
        Jedis jedis = RedisUtils.getJedis();
        List<String> hvals = jedis.hvals(key);
        RedisUtils.returnJedis(jedis);
        return hvals;
    }

    public static long hdel(String key,String filed){
        Jedis jedis = RedisUtils.getJedis();
        Long hdel = jedis.hdel(key,filed);
        RedisUtils.returnJedis(jedis);
        return hdel;
    }

    public static String getAreaNames(String key) {
        Jedis jedis = RedisUtils.getJedis();
        String area_all_sqh = jedis.get(key);
        RedisUtils.returnJedis(jedis);
        return area_all_sqh;
    }

    public static boolean exists(String key){
        Jedis jedis = RedisUtils.getJedis();
        Boolean exists = jedis.exists(key);
        RedisUtils.returnJedis(jedis);
        return exists;
    }

}
