package com.study.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    @ReturnLog
    public String myMethod(String input) {
        return "Hello, " + input;
    }
}
