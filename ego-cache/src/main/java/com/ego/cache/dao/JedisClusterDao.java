package com.ego.cache.dao;

/**
 * @author boge.peng
 * @create 2019-03-14 23:21
 */
public interface JedisClusterDao {

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    Boolean exists(String key);

    /**
     * 删除
     * @param key
     * @return
     */
    Long del(String key);

    /**
     * 设置值
     * @param key
     * @param value
     * @return
     */
    String set(String key,String value);

    /**
     * 取值
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置key的过期时间
     * @param key
     * @param seconds
     * @return
     */
    Long expire(String key,int seconds);
}
