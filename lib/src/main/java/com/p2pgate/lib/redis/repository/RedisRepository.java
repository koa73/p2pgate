package com.p2pgate.lib.redis.repository;


import com.p2pgate.lib.redis.domain.TempData;

/**
 *
 *
 * Created by OAKutsenko on 20.04.2017.
 */
public interface RedisRepository {

    public void save(TempData transaction);
    public TempData findById(String key);
    public TempData getById(String key);
    public void delete(String key);
}
