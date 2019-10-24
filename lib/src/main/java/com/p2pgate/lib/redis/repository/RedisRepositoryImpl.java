package com.p2pgate.lib.redis.repository;

import com.p2pgate.lib.redis.domain.TempData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


import java.util.concurrent.TimeUnit;

/**
 *
 *
 * Created by OAKutsenko on 20.04.2017.
 */
@Repository
public class RedisRepositoryImpl implements RedisRepository {

    @Autowired
    private RedisTemplate<String, TempData> redisTemplate;

    @Override
    public void save(final TempData tmpData) {

        final String key = tmpData.getOperId();
        BoundValueOperations<String, TempData> boundValueOperations = redisTemplate.boundValueOps(key);
        boundValueOperations.set(tmpData);
        boundValueOperations.expire(10, TimeUnit.MINUTES);

        //redisTemplate.opsForValue().set(transaction.getOperId(), transaction);
    }

    @Override
    public TempData findById(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public TempData getById(String key) {

        TempData tmpData = redisTemplate.opsForValue().get(key);
        delete(key);
        return tmpData;
    }

    @Override
    public void delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

}
