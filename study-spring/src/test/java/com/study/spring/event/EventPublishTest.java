package com.study.spring.event;

import com.study.spring.ApplicationTest;
import com.study.spring.event.message.PushEventMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EventPublishTest extends ApplicationTest {
    @Autowired
    private PushEventPublish publish;

    /**
     * 事件发布订阅
     */
    @Test
    public void publish() {
        PushEventMessage message = new PushEventMessage(12245L, System.currentTimeMillis(), "hello~~~");
        // 发布消息
        publish.publish(message);
    }

}