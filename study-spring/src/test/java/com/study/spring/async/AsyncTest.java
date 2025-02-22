package com.study.spring.async;

import com.study.spring.ApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
public class AsyncTest extends ApplicationTest {

    @Autowired
    private IAsyncService asyncService;

    // 无返回值异步执行，使用的配置全局线程池
    @Test
    public void asyncMethodNoReturn() throws InterruptedException {
        asyncService.asyncMethodNoReturn();
    }

    // 有返回值异步执行，使用的指定的线程池
    @Test
    public void asyncMethodHasReturn() throws InterruptedException, ExecutionException {
        Future<String> stringFuture = asyncService.asyncMethodHasReturn();
        String s = stringFuture.get();
        log.info("异步返回结果：{}",s);
    }
}