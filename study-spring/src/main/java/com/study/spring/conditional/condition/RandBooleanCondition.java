package com.study.spring.conditional.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <h1>布尔随机数条件检验</h1>
 * */
public class RandBooleanCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取配置信息
        String type = context.getEnvironment().getProperty("conditional.rand.type");
        return "boolean".equalsIgnoreCase(type);
    }
}
