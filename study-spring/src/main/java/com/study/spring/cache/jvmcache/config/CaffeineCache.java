package com.study.spring.cache.jvmcache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CaffeineCache {

    private final LoadingCache<Integer, Optional<SystemConfig>> systemConfigLoadingCache =
            Caffeine.newBuilder()
                    .initialCapacity(10)
                    .maximumSize(100)
                    .expireAfterWrite(60, TimeUnit.SECONDS)
                    .refreshAfterWrite(60, TimeUnit.SECONDS)
//                    .removalListener((key, val, removalCache) -> {log.info("{}, {}, {}",);})
                    .recordStats()
                    .build(key -> Optional.empty());
}
