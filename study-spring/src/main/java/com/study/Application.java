package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <h1>应用程序启动入口</h1>
 */
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) 排除DataSourceAutoConfiguration自动装配
@SpringBootApplication
// proxyTargetClass = true 使用cglib代理，默认是jdk代理
@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy(exposeProxy = true) // 自己调用自己
@MapperScan("com.study.**.mapper")
// 扫描自定义包，包名不一致需要单独扫描，如果是com.study包下就不用扫描
@ComponentScan({"com.custom.okhttp", "com.study.spring"})
// 老项目的properties可以通过这种方式导入
@ImportResource(locations = "classpath:applicationContext.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // META-INF spring.factories: org.springframework.boot.autoconfigure.EnableAutoConfiguration
}
