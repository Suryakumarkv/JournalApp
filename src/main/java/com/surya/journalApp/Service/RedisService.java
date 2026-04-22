package com.surya.journalApp.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surya.journalApp.api.response.WeatherResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // ← was raw RedisTemplate

    @PostConstruct
    public void checkRedisConnection() {
        String value = (String) redisTemplate.opsForValue().get("cloud_test");
        System.out.println("🔥 Redis read test: " + value);
    }

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);


            if (o == null) {
                log.warn("Cache miss for key: {}", key);
                return null;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(o.toString(), entityClass);

        } catch (Exception e) {
            log.error("Exception", e);
            return null;
        }
    }

    public void set(String key, Object o, Long expireTime) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonValue, expireTime, TimeUnit.SECONDS);
            log.info("✅ Saved to Redis → key: {}", key); // ← moved OUT of catch
        } catch (Exception e) {
            log.error("❌ Redis save failed for key: {}", key, e);
        }
    }
}