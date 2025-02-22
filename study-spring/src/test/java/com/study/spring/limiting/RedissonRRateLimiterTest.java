package com.study.spring.limiting;

import com.study.spring.ApplicationTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedissonRRateLimiterTest extends ApplicationTest {


    @Test
    void useLuaRateLimiter() throws InterruptedException {
        RedissonRRateLimiter redissonRRateLimiter = new RedissonRRateLimiter();
        redissonRRateLimiter.useLuaRateLimiter();
    }
}