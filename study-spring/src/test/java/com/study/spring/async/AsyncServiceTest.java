package com.study.spring.async;

import com.study.spring.ApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncServiceTest extends ApplicationTest {

    @Autowired
    private IAsyncService asyncService;

    @Test
    public void asyncMethodNoReturn() throws InterruptedException {
        asyncService.asyncMethodNoReturn();
    }

    @Test
    public void asyncMethodHasReturn() throws InterruptedException, ExecutionException {
        Future<String> stringFuture = asyncService.asyncMethodHasReturn();
        String s = stringFuture.get();
        System.out.println("return = " + s);
    }
}