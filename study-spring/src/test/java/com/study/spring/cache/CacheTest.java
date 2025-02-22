package com.study.spring.cache;

import com.study.spring.ApplicationTest;
import com.study.spring.cache.jvmcache.config.CaffeineCache;
import com.study.spring.cache.jvmcache.config.GuavaLoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CacheTest extends ApplicationTest {

    @Autowired
    private GuavaLoadingCache guavaLoadingCache;
    @Autowired
    private CaffeineCache caffeineCache;

    @Test
    public void guavaLoadingCache() {

    }

    @Test
    public void caffeineCache() {

    }

}
