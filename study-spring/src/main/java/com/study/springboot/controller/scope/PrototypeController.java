package com.study.springboot.controller.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多例，每个线程都有独立的bean
 */
@Scope(value = "prototype")
@RestController
public class PrototypeController {

    private int number = 0;

    @GetMapping(value = "/api/v2/number1")
    public int number1() {
        number ++;
        System.out.println(this.number);
        return this.number;
    }

    @GetMapping(value = "/api/v2/number2")
    public int number2() {
        number ++;
        System.out.println(this.number);
        return this.number;
    }
}
