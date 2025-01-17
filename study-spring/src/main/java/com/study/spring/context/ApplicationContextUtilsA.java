package com.study.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ApplicationContextUtilsA implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if (Objects.isNull(context)) {
            context = applicationContext;
            // TODO 做一些其他的事情
        }
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
