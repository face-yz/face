package com.ylf.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author: leifeng.ye
 * @date: 2019-12-18
 * @desc:
 */
@Component
public class FaceRedisTemplate {
    @Autowired
    public RedisTemplate redisTemplate;

    @Bean
    public RedisTemplate getRedisTemplate() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
