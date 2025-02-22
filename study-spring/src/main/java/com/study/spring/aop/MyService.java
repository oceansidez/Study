package com.study.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    @ReturnLog(prefix = "自定义切面")
    public String myMethod(String input) {
        return "Hello, " + input;
    }
}
