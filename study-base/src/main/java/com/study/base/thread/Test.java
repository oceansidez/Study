package com.study.base.thread;

import java.util.HashMap;

/**
 * 不同线程轮流答应a，b，c
 */
public class Test {
    private static final Object lock = new Object();
    private static int count = 1;
    private static final int maxCount = 10;

    public static void main(String[] args) {
        new HashMap<>();
        // 线程1打印 'a'
        new Thread(() -> {
            while (count <= maxCount) {
                synchronized (lock) {
                    // 等待条件，直到是该打印 'a'
                    while (count % 3 != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 打印并增加计数
                    System.out.println(Thread.currentThread().getName() + " a");
                    count++;
                    lock.notifyAll(); // 通知其他线程
                }
            }
        }, "线程1").start();

        // 线程2打印 'b'
        new Thread(() -> {
            while (count <= maxCount) {
                synchronized (lock) {
                    // 等待条件，直到是该打印 'b'
                    while (count % 3 != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 打印并增加计数
                    System.out.println(Thread.currentThread().getName() + " b");
                    count++;
                    lock.notifyAll(); // 通知其他线程
                }
            }
        }, "线程2").start();

        // 线程3打印 'c'
        new Thread(() -> {
            while (count <= maxCount) {
                synchronized (lock) {
                    // 等待条件，直到是该打印 'c'
                    while (count % 3 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 打印并增加计数
                    System.out.println(Thread.currentThread().getName() + " c");
                    count++;
                    lock.notifyAll(); // 通知其他线程
                }
            }
        }, "线程3").start();
    }
}
