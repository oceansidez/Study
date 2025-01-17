package com.study.spring.conditional.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <h1>整型随机数条件检验</h1>
 * */
public class RandIntegerCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        String type = context.getEnvironment().getProperty("conditional.rand.type");
        return "int".equalsIgnoreCase(type);
    }
}
