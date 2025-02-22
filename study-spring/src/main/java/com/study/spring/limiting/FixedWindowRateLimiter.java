package com.study.spring.limiting;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1>固定窗口计数器算法伪代码实现</h1>
 * */
@SuppressWarnings("all")
public class FixedWindowRateLimiter {

    // 阈值
    private static Integer QPS = 2;
    // 时间窗口, 毫秒
    private static long TIME_WINDOWS = 1000;
    // 计数器
    private static AtomicInteger REQ_COUNT = new AtomicInteger();

    private static long START_TIME = System.currentTimeMillis();

    /**
     * <h2>尝试获取可执行流量</h2>
     * */
    public synchronized static boolean tryAcquire() {

        // 是否到达了下一个时间窗口, 是, 需要重置计数器和时间窗口的开始时间
        if ((System.currentTimeMillis() - START_TIME) > TIME_WINDOWS) {
            REQ_COUNT.set(0);
            START_TIME = System.currentTimeMillis();
        }

        // 当前时间窗口的流量是否超过了预设的阈值
        return REQ_COUNT.incrementAndGet() <= QPS;
    }
}
