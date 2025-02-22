package com.study.spring.aop;

import com.study.spring.ApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AopTest extends ApplicationTest {

    @Autowired
    MyService myService;

    // 切面测试
    @Test
    public void test() {
        // 调用方法并检查切面的行为
        String world = myService.myMethod("World");
    }
}