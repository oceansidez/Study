package com.study.spring.event.inner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * spring 内部的Listener
 */
@Slf4j
@Component
public class SpringInnerListener {

    /**
     * Spring 应用上下文被初始化或刷新 时触发
     *
     * @param event
     */
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        log.info("bean初始化、刷新触发....");
    }

    /**
     * Spring 应用上下文关闭 时触发
     *
     * @param event
     */
    @EventListener
    public void handleContextClose(ContextClosedEvent event) {
        log.info("应用上下文关闭时触发....");
    }

    /**
     * 应用完全启动后才触发
     *
     * @param event
     */
    @EventListener
    public void handleApplicationReadyEvent(ApplicationReadyEvent event) {
        log.info("应用完全启动后才触发....");
    }
}
