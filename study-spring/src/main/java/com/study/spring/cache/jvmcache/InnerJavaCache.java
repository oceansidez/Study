package com.study.spring.cache.jvmcache;

import com.study.spring.cache.jvmcache.config.SystemConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <h1>使用 Java 内置的数据结构或者是自己定义的数据结构构造 JVM 本地缓存</h1>
 * */
@Slf4j
@Component
public class InnerJavaCache implements InitializingBean {

    private static Map<Integer, SystemConfig> configId2ConfigObj = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        // 初始化和刷新 SystemConfig
        initAndRefreshSystemConfig();
    }

    /**
     * 使用异步进行刷新
     */
    private void initAndRefreshSystemConfig() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(
                1, // 线程池大小
                new BasicThreadFactory.Builder() // 使用 BasicThreadFactory 创建线程工厂
                        .namingPattern("init-refresh-system-config") // 为线程指定名称模式
                        .daemon(true) // 设置线程为守护线程
                        .build() // 创建线程工厂
        );
        // 提交一个每3分钟执行一次的异步任务,刷新配置对象
        executorService.scheduleAtFixedRate(() -> {
            // 1. 获取 system config
            // 2. put 到 configId2ConfigObj 中去
        }, 0, 3, TimeUnit.MINUTES);
    }

    public SystemConfig getConfigById(Integer configId) {
        return configId2ConfigObj.get(configId);
    }
}
