package com.study.spring.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

// 使用@Bean,在Async中使用
@Slf4j
@Configuration
public class AsyncConfigurationB {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        // 获取处理器
        int cpuCount = Runtime.getRuntime().availableProcessors();
        // 构建线程池 使用spring封装的
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(cpuCount * 2);
        taskExecutor.setMaxPoolSize(cpuCount * 4);
        taskExecutor.setQueueCapacity(50000);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("my-asyncB-");
        // 拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 等待线程完成才关闭现称池
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 最大等待60s
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
