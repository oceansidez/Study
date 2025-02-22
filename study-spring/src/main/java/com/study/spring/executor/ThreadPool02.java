package com.study.spring.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用submit提交任务,可以获取返回值,异常不会抛出,在获取值时才抛出
 */
public class ThreadPool02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //基于Executor框架实现线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                //Executors.defaultThreadFactory(),
                //Executors.privilegedThreadFactory(),
                new MyThreadFactory("pay-thread-pool-"),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < 3; i++) {
            Future<?> submit = threadPoolExecutor.submit(new MyRunnable(i));
            Object o = submit.get();
            System.out.println("o = " + o);
        }

        threadPoolExecutor.shutdown();
    }

    static class MyThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(1); //线程编号
        private final String namePrefix; //线程名称的前缀

        public MyThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    static class MyRunnable implements Callable {

        private int i;

        public MyRunnable(int i) {
            this.i = i;
        }

        @Override
        public Object call() throws Exception {
//            int n = 1 / 0;
            System.out.println(this.i + "任务执行......" + Thread.currentThread().getName());
            return this;
        }

        @Override
        public String toString() {
            return "MyRunnable{" +
                    "i=" + i +
                    '}';
        }
    }
}
