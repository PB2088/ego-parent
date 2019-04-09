package com.ego.cache.dao.impl;

import com.ego.cache.dao.JedisClusterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

/**
 * @author boge.peng
 * @create 2019-03-14 23:28
 */
@Repository
public class JedisClusterDaoImpl implements JedisClusterDao {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key,value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key,seconds);
    }
}
