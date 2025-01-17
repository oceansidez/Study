package com.study.spring.aop;

import com.study.spring.ApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MyServiceTest extends ApplicationTest {
    @Autowired
    MyService myService;

    @Test
    public void test() {
        // 调用方法并检查切面的行为
        String world = myService.myMethod("World");
        System.out.println("world = " + world);
    }
}