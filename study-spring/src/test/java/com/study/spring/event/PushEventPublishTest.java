package com.study.spring.event;

import com.study.spring.ApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PushEventPublishTest extends ApplicationTest {
    @Autowired
    private PushEventPublish publish;

    @Test
    public void publish() {
        PushEventMessage message = new PushEventMessage(12245L, System.currentTimeMillis(), "hello~~~");
        publish.publish(message);
    }

}