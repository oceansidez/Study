package com.study.spring.conditional;

import com.study.spring.ApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ConditionalTest extends ApplicationTest {
    @Autowired
    private RandomDataSupplier randomDataSupplier;

    // 条件注入
    @Test
    public void conditionalTest(){
        Object rand = randomDataSupplier.rand();
        log.info("{}",rand.getClass().getName());
    }
}
