package com.study.spring.bean;

import com.study.spring.service.IAccountService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * 并没有加入spring的bean中通过CustomBeanRegistry把MessageHandler做成一个Bean注册到IOC，并自己完成初始化
 */
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
        log.info("自定义类 MessageHandler start执行.....");
    }

    public void close() {
        log.info("自定义类 MessageHandler 执行close.....");
    }
}
