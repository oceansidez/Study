package com.study.spring.event.listener;

import com.study.spring.event.PushEvent;
import com.study.spring.event.message.PushEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 推送事件监听 第一种
 */
@Slf4j
@Component
public class PushEventListener01 implements ApplicationListener<PushEvent> {

    @Async("threadPoolTaskExecutor")
    @Override
    public void onApplicationEvent(PushEvent pushEvent) {
        PushEventMessage message = (PushEventMessage) pushEvent.getSource();
        log.info("收到消息 {}", message);
    }
}
