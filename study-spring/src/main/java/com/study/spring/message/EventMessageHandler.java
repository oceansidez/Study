package com.study.spring.message;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <h1>事件处理器，即消费者</h1>
 */
@Slf4j
public class EventMessageHandler implements EventHandler<EventMessage>, WorkHandler<EventMessage> {

    private final String handlerName;

    public EventMessageHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    @Override
    public void onEvent(EventMessage event, long sequence, boolean endOfBatch) throws Exception {
        log.info("...");
    }

    @Override
    public void onEvent(EventMessage event) throws Exception {
        log.info("...");
    }
}
