package com.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // ==================== key操作 ====================

    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            System.err.println("Redis hasKey failed: " + e.getMessage());
            return false;
        }
    }

    public void delete(String... key) {
        try {
            if (key != null && key.length > 0) {
                if (key.length == 1) {
                    redisTemplate.delete(key[0]);
                } else {
                    redisTemplate.delete(CollectionUtils.arrayToList(key));
                }
            }
        } catch (Exception e) {
            System.err.println("Redis delete failed: " + e.getMessage());
        }
    }

    public void delete(Collection<String> keys) {
        try {
            redisTemplate.delete(keys);
        } catch (Exception e) {
            System.err.println("Redis delete failed: " + e.getMessage());
        }
    }

    public Long getExpire(String key) {
        try {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.err.println("Redis getExpire failed: " + e.getMessage());
            return null;
        }
    }

    // ==================== String操作 ====================

    public Object get(String key) {
        try {
            return key == null ? null : redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            System.err.println("Redis get failed: " + e.getMessage());
            return null;
        }
    }

    public void set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            System.err.println("Redis set failed: " + e.getMessage());
        }
    }

    public void set(String key, Object value, long timeout, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        } catch (Exception e) {
            System.err.println("Redis set failed: " + e.getMessage());
        }
    }

    // ==================== Hash操作 ====================

    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    public void hset(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    public void hdelete(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public void hmset(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    // ==================== List操作 ====================

    public List<Object> lGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public void lSet(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    // ==================== Set操作 ====================

    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public void sSet(String key, Object... values) {
        redisTemplate.opsForSet().add(key, values);
    }
}
