package com.study.spring.limiting;

import com.google.common.util.concurrent.RateLimiter;

/**
 * <h1>使用 guava 中的令牌桶限流算法实现</h1>
 * */
@SuppressWarnings("all")
public class TokenBucketRateLimiter {

    // 令牌桶每秒10个令牌
    private final static RateLimiter rateLimiter = RateLimiter.create(10);

    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
}
