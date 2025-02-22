package com.study.spring.cache.jvmcache.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <h1>guava loading cache 的使用</h1>
 */
@Slf4j
@Component
public class GuavaLoadingCache {

    private final LoadingCache<Integer, Optional<SystemConfig>> systemConfigLoadingCache =
            CacheBuilder.newBuilder()
                    // 设置缓存的并发级别，即缓存的最大并发访问线程数, 当前 JVM 中可用的处理器核心数
                    .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                    // 缓存的初始容量
                    .initialCapacity(10)
                    // 缓存的最大容量
                    .maximumSize(100)
                    // 启用缓存的统计功能,可以 通过 cache.stats() 方法来获取这些统计信息
                    .recordStats()
                    // 缓存元素的过期策略,设置每个缓存项在写入后的 60 秒内有效
                    .expireAfterWrite(60, TimeUnit.SECONDS)
                    // 缓存项在多长时间没有被访问后自动过期并被移除
//                    .expireAfterAccess(60, TimeUnit.SECONDS)
                    // 缓存项的刷新策略。缓存项在 60 秒后被访问，将会自动刷新该缓存项。
                    .refreshAfterWrite(60, TimeUnit.SECONDS)
                    // 设置一个监听器，当缓存中的元素被移除时监听
                    .removalListener(notification -> {
                        log.info("{}, {}, {}", notification.getKey(), notification.getValue(), notification.getCause());
                    })
                    .build(new SystemConfigCacheLoader());

    public static class SystemConfigCacheLoader extends CacheLoader<Integer, Optional<SystemConfig>> {
        // 加载缓存项，当缓存中没有指定 key 的值时，CacheLoader 会调用 load 方法去加载该值
        @Override
        public Optional<SystemConfig> load(Integer key) throws Exception {
            return Optional.empty();
        }
    }

    public SystemConfig getConfigById(Integer configId) {
        try {
            return systemConfigLoadingCache.get(configId).orElse(null);
        } catch (ExecutionException ex) {
            return null;
        }
    }

    public void clean() {
        // 单个清除
        systemConfigLoadingCache.invalidate(1);
        // 批量清除
        systemConfigLoadingCache.invalidateAll(Arrays.asList(1, 2, 3));
        // 清除所有的缓存项
        systemConfigLoadingCache.invalidateAll();
    }

    public void systemConfigLoadingCacheStat() {
        log.info("stat info: {}", systemConfigLoadingCache.stats());
    }
}
