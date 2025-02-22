package com.study.springboot.conditional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 使用自定义条件注解
 */
@Slf4j
@Configuration
// 通过 执行ConditionalOnCustomCondition中的matches方法 间接的执行了自定义的条件注解。
// 因此不仅需要标注Conditional指定具体的条件还要指定ConditionalOnCustom
@Conditional(value = ConditionalOnCustomCondition.class)
@ConditionalOnCustom(value = "on")
public class CustomConfiguration {
}
