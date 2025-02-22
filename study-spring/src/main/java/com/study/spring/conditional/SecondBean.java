package com.study.spring.conditional;

import com.study.spring.conditional.condition.SecondCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * 通过Conditional类注解控制bean的生成
 */
@Slf4j
@Component
@Conditional(SecondCondition.class)
public class SecondBean {

    public void print() {
        log.info("通过Conditional类注解控制bean的生成....");
    }
}
