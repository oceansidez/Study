package com.study.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <h1>方法返回打印日志切面</h1>
 */
@Slf4j
@Aspect
@Component
public class ReturnLogAspect {

    @Pointcut("@annotation(com.study.spring.aop.ReturnLog)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获取目标类名称、方法名称
        String clazzName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        // 调用目标方法
        Object result = joinPoint.proceed();
        // 获取切点信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ReturnLog returnLog = method.getAnnotation(ReturnLog.class);
        String prefix = returnLog.prefix();
        // 记录日志
        log.info("切面执行，返回结果为" + prefix + " " + result);

        return result;
    }
}
