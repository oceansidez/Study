package com.study.spring.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 推送事件发布
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PushEventPublish {

    // 第一种 这个继承了 ApplicationEventPublisher
    private final ApplicationContext applicationContext;
    // 第二种
    private final ApplicationEventPublisher publisher;

    /**
     * 发布事件消息
     *
     * @param message
     */
    public void publish(PushEventMessage message) {
        applicationContext.publishEvent(new PushEvent(message));
        publisher.publishEvent(new PushEvent(message));
    }
}
