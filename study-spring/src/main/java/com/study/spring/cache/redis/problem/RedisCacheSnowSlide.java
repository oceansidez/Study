package com.study.spring.cache.redis.problem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <h1>redis 缓存雪崩问题</h1>
 * */
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisCacheSnowSlide {

    private final RedissonClient redissonClient;

    /**
     * <h2>第一种解决方案: 随机过期时间</h2>
     * */
    public void solution01() {

        int defaultExpireSeconds = 60;
        int targetExpireSeconds = defaultExpireSeconds + randomExpireSecond();
        redissonClient.getBucket("r:expire:0322").setAsync(new Object(),
                targetExpireSeconds, TimeUnit.SECONDS);
    }

    private int randomExpireSecond() {
        return RandomUtils.nextInt(10, 100);
    }

    /**
     * <h2>第二种解决方案: 互斥锁， 只有一个线程构建数据</h2>
     * */
    public void solution02() {

        String dataKey = "r:expire:0322";
        String cacheData = (String) redissonClient.getBucket(dataKey).get();

        // 如果不存在缓存, 使用互斥锁实现缓存的刷写
        if (StringUtils.isBlank(cacheData)) {
            RLock lock = redissonClient.getLock("r:lock:0322");
            try {
                // 成功获取到锁
                if (lock.tryLock(1000, 2000, TimeUnit.MILLISECONDS)) {
                    // 从数据库中获取数据, set to cache
                    // ....
                }
            } catch (Exception ex) {
                log.error("....");
            } finally {
                // 释放锁
                lock.unlock();
            }
        } else {
            // 存在缓存

        }
    }
}
