package com.study.spring.bean;

import com.study.spring.service.IAccountService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

@Slf4j
public class MessageHandler {

    private final String handlerName;
    private final IAccountService accountService;
    private final ExecutorService executorService;

    public MessageHandler(String handlerName, IAccountService accountService, ExecutorService executorService) {
        this.handlerName = handlerName;
        this.accountService = accountService;
        this.executorService = executorService;
    }

    public void start() {
        log.info("start.....");
    }

    public void close() {
        log.info("close.....");
    }
}
