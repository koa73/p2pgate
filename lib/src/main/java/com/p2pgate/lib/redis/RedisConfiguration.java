package com.p2pgate.lib.redis;

import com.p2pgate.lib.redis.domain.TempData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;



/**
 *
 * Created by OAKutsenko on 20.04.2017.
 */

@Configuration
public class RedisConfiguration {

    @Autowired(required = false)
    private JedisConnectionFactory jedisConnFactory;

    @Bean
    public RedisTemplate<String, TempData> redisTemplate() {
        RedisTemplate<String, TempData> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }
}
