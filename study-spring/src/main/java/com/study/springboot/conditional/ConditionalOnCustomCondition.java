package com.study.springboot.conditional;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 自定义注解实现
 */
public class ConditionalOnCustomCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 从 metadata 中获取 @ConditionalOnCustom 注解的属性值, 属性名：值
        // ConditionalOnCustom.class.getName()：注解的全限定类名
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnCustom.class.getName());
        if (MapUtils.isNotEmpty(annotationAttributes)) {
            //  Map 中获取 value 属性的值
            String value = (String) annotationAttributes.get("value");
            return StringUtils.equals(value, "on");
        }
        return false;
    }
}
