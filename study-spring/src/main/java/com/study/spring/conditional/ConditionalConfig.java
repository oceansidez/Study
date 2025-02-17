package com.study.spring.conditional;


import com.study.spring.conditional.condition.RandBooleanCondition;
import com.study.spring.conditional.condition.RandIntegerCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * <h1>随机数生成器条件配置</h1>
 * */
@Configuration
public class ConditionalConfig {

    @Bean
    // matches 成立才会注入
    @Conditional(RandBooleanCondition.class)
    public RandomDataSupplier<Boolean> randBooleanComponent() {

        return new RandomDataSupplier<>(() -> {
            Random random = new Random();
            return random.nextBoolean();
        });
    }

    @Bean
    @Conditional(RandIntegerCondition.class)
    public RandomDataSupplier<Integer> randIntComponent() {

        return new RandomDataSupplier<>(() -> {
            Random random = new Random();
            return random.nextInt(1024);
        });
    }
}
