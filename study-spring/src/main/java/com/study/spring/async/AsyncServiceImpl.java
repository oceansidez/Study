package com.study.spring.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncServiceImpl implements IAsyncService {

    @Async
    @Override
    public void asyncMethodNoReturn() throws InterruptedException {
        // 业务逻辑
        log.info("异步无返回值执行了.... ");
    }

    @Async("taskExecutor")
    @Override
    public Future<String> asyncMethodHasReturn() throws InterruptedException {
        // 业务逻辑
        log.info("异步有返回值执行了.... ");
        return new AsyncResult<>("测试返回值");
    }
}
