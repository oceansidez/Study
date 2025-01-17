package com.study.spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ApplicationContextUtilsB implements ApplicationListener<ContextRefreshedEvent> {

    private static ApplicationContext context;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (Objects.isNull(context)) {
            context = event.getApplicationContext();
        }
    }

    //
}
