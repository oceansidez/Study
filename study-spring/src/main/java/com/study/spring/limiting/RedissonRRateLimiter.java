package com.study.spring.limiting;

import com.study.spring.context.ApplicationContextUtilsA;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 使用redis实现限流
 */
public class RedissonRRateLimiter {

    // 使用 redisson实现限流
    public boolean useRRateLimiter() {
        RedissonClient redissonClient = ApplicationContextUtilsA.getContext().getBean(RedissonClient.class);
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("r.qinyi.limiter");
        // 系统的速率限制,每分钟最多允许 100 次请求,单位是 1 分钟,单位时间为分钟
        rateLimiter.trySetRate(RateType.OVERALL, 100, 1, RateIntervalUnit.MINUTES);
        // 会阻塞当前线程，直到获取到一个令牌为止
        rateLimiter.acquire();
        // 非阻塞式，尝试在 5 秒内获取 1 个令牌
        return rateLimiter.tryAcquire(1, 5, TimeUnit.SECONDS);
    }

    // 使用lua脚本实现限流
    public void useLuaRateLimiter() throws InterruptedException {
        StringRedisTemplate stringRedisTemplate= ApplicationContextUtilsA.getContext().getBean(StringRedisTemplate.class);
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limiting/concurrent_request_rate_limiter.lua")));

        for (int i = 0; i < 112; i++) {
            // 相当于每秒一个key
            String key = String.valueOf(System.currentTimeMillis() / 1000); // limit:1705664721
            // 限流100并发,20秒过期
            Long result = stringRedisTemplate.execute(redisScript, Arrays.asList(key), String.valueOf(100),String.valueOf(20));
            if (result == 0) {
                //需要限流
                System.out.println("限流了......");
            }
        }
    }
}
