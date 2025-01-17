package com.study.spring.async;

import java.util.concurrent.Future;

public interface IAsyncService {
    /**
     * 无返回值
     *
     * @throws InterruptedException
     */
    void asyncMethodNoReturn() throws InterruptedException;

    /**
     * 有返回值
     *
     * @return
     * @throws InterruptedException
     */
    Future<String> asyncMethodHasReturn() throws InterruptedException;

}
