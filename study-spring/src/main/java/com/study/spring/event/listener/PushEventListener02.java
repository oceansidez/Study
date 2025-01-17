package com.study.spring.event.listener;

import com.study.spring.event.PushEvent;
import com.study.spring.event.PushEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 第二种方式
 */
@Slf4j
@Component
public class PushEventListener02 {

    @Async("threadPoolTaskExecutor")
    // PushEvent.class 可省略
    @EventListener(PushEvent.class)
    public void receive(PushEvent pushEvent) {
        PushEventMessage message = (PushEventMessage) pushEvent.getSource();
        log.info("收到消息 {}", message);
    }
}
