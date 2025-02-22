package com.study.spring.limiting;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1>滑动窗口计数器算法伪代码实现</h1>
 * */
@SuppressWarnings("all")
public class SlidingWindowRateLimiter {

    // 阈值
    private int qps = 2;
    // 时间窗口总大小, 毫秒
    private long windowSize = 1000;
    // 多少个子窗口
    private int windowCount = 10;
    // 窗口列表
    private WindowInfo[] windowArray = new WindowInfo[windowCount];

    public SlidingWindowRateLimiter(int qps) {
        this.qps = qps;
        long currentTimeMills = System.currentTimeMillis();
        for (int i = 0; i < windowArray.length; i++) {
            windowArray[i] = new WindowInfo(currentTimeMills, new AtomicInteger(0));
        }
    }

    /**
     * <h2>滑动窗口计数器算法实现</h2>
     * 1. 计算当前时间窗口
     * 2. 更新当前窗口计数 & 重置过期窗口计数
     * 3. 当前 QPS 是否超过限制
     * */
    public synchronized boolean tryAcquire() {

        long currentTimeMills = System.currentTimeMillis();
        // 1. 计算当前时间窗口
        int currentIndex = (int) (currentTimeMills % windowSize / (windowSize / windowCount));
        // 2. 更新当前窗口计数 & 重置过期窗口计数
        int sum = 0;
        for (int i = 0; i < windowArray.length; ++i) {
            WindowInfo windowInfo = windowArray[i];
            if ((currentTimeMills - windowInfo.getTime()) > windowSize) {
                windowInfo.getNumber().set(0);
                windowInfo.setTime(currentTimeMills);
            }

            if (currentIndex == i && windowInfo.getNumber().get() < qps) {
                windowInfo.getNumber().incrementAndGet();
            }

            sum = sum + windowInfo.getNumber().get();
        }

        // 3. 当前 QPS 是否超过限制
        return sum <= qps;
    }

    @Data
    @AllArgsConstructor
    private class WindowInfo {
        // 窗口的开始时间
        private long time;
        // 计数器
        private AtomicInteger number;
    }
}
