package com.study.spring.message.impl;

import com.study.spring.message.EventMessageDisruptor;
import com.study.spring.message.EventMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkHandlerEventMessageDisruptor extends EventMessageDisruptor {

    @Override
    protected void handleEvent() {
        disruptor.handleEventsWith(
                new EventMessageHandler("work-handle-01"),
                new EventMessageHandler("work-handle-02")
        );
    }
}