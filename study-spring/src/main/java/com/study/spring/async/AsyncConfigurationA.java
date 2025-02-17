package com.study.spring.async;


import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <h1>异步任务的相关配置</h1>
 * 全局的配置默认线程池 AsyncConfigurer，也可设置@Bean的方式，在@Async中指定线程池
 * 如果没配置使用的默认线程，注意需要开启 @EnableAsync
 */
@Slf4j
@Configuration
public class AsyncConfigurationA implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        // 获取处理器
        int cpuCount = Runtime.getRuntime().availableProcessors();
        // 构建线程池 使用spring封装的
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(cpuCount * 2);
        taskExecutor.setMaxPoolSize(cpuCount * 4);
        taskExecutor.setQueueCapacity(50000);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("my-asyncA-");
        // 拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 等待线程完成才关闭现称池
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 最大等待60s
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * 异步异常处理
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                log.error(method.getName() + ex.getMessage() + "异步执行出现异常....");
            }
        };
    }
}
