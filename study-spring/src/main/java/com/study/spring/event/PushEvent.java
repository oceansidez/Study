package com.study.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * <h1>推送事件</h1>
 */
public class PushEvent extends ApplicationEvent {
    public PushEvent(Object source) {
        super(source);
    }
}
