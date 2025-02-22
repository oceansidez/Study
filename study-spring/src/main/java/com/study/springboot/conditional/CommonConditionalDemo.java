package com.study.springboot.conditional;

import com.study.spring.service.IAccountService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * <h1>SpringBoot 常用内置的条件注解</h1>
 */
public class CommonConditionalDemo {

    /**
     * 条件装配
     */
    @Configuration
    //  当配置文件中 custom.engineering 的值被明确设置时，相关的 bean 才会被加载，场景：属性缺失时不希望加载 bean
    //  @ConditionalOnProperty(prefix = "custom", value = "engineering", matchIfMissing = false)
    // 当配置文件中 custom.engineering 的值为 true 时，相关的 bean 才会被加载。场景：在开启某个开关（custom.engineering = true）时才加载某个 bean。
    @ConditionalOnProperty(prefix = "custom", name = "engineering", havingValue = "true")
    public class EnableAccountConfiguration {

        // ....
    }

    /**
     * 加在类中
     */
    @Configuration
    @ConditionalOnBean(EnableAccountConfiguration.class)
    public class customConfig {

    }

    /**
     * 加载Bean方法中
     *
     * @return
     */
    @Bean(value = "customDataSource")
    @ConditionalOnBean(IAccountService.class)
    public ConditionDemo customDataSource() {
        return new ConditionDemo();
    }

    public class ConditionDemo{

    }

}
