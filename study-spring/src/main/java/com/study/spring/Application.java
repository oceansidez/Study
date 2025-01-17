package com.study.spring;

//import com.study.spring.bean.MessageHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <h1>应用程序启动入口</h1>
 */
@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy(exposeProxy = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // META-INF spring.factories: org.springframework.boot.autoconfigure.EnableAutoConfiguration
    // mvn clean install -Dmaven.test.skip=true
    // pom.xml
    // ComponentScan
}
