package com.study.spring.event.inner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * spring 内部的Listener
 */
@Slf4j
@Component
public class SpringInnerListener {

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        log.info("bean都初始化成功....");
    }
}
