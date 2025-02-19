package com.study.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 可以动态添加、修改或删除 BeanDefinition
 * 将bean  MessageHandler 注册到ioc中
 */
@Slf4j
@Configuration
public class CustomBeanRegistry implements BeanDefinitionRegistryPostProcessor {

    public static final String PROCESSOR_BEAN_NAME = "accountServiceImpl";
    public static final String BEAN_NAME_PREFIX = "multiMessageHandler";

    /**
     * bean 初始化与销毁的执行方法
     */
    public static final String INIT_METHOD_NAME = "start";
    public static final String DESTROY_METHOD_NAME = "close";

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String beanName = buildBeanName("qinyi");
        BeanDefinition definition = buildBeanDefinition("qinyi");
        registry.registerBeanDefinition(beanName, definition);
        log.info("自定义BeanDefinition注入到容器成功......");
    }

    /**
     * <h2>生成一个新的 Bean 元数据定义</h2>
     */
    public GenericBeanDefinition newBeanDefinition() {
        // 设置bena的元数据信息
        GenericBeanDefinition definition = new GenericBeanDefinition();
        // bean 作用域
        definition.setScope(GenericBeanDefinition.SCOPE_SINGLETON);
        // 是否是抽象（抽象不会被spring实例化）
        definition.setAbstract(false);
        // 延迟实例化，默认也是false，容器启动时就被初始化
        definition.setLazyInit(false);
        // 设置自动装配模式，不使用自动装配自己手动装配
        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_NO);
        // 设置依赖检查 不使用依赖检查
        definition.setDependencyCheck(GenericBeanDefinition.DEPENDENCY_CHECK_NONE);
        // bean的依赖的名称 依赖的bean
        definition.setDependsOn();
        // bean 是否连接到后续的bean 通常用不到，了解
        definition.setAutowireCandidate(false);
        // 是否是主要的候选 bean 多个同类型的bean才会用
        definition.setPrimary(false);
        // 是否允许访问非公共函数和方法，默认true
        definition.setNonPublicAccessAllowed(true);
        // 是否在宽松模式下进行解析，默认false 了解
        definition.setLenientConstructorResolution(false);
        // 启动时的初始化方法
        definition.setInitMethodName(INIT_METHOD_NAME);
        // 销毁时始化方法
        definition.setDestroyMethodName(DESTROY_METHOD_NAME);
        // bean 是否是合成的（不是由spring定义出来的），自己定义的所以设置为true
        definition.setSynthetic(true);
        // 设置角色，应用程序级别
        definition.setRole(GenericBeanDefinition.ROLE_APPLICATION);

        return definition;
    }

    private String buildBeanName(String handlerName) {
        return String.format("%s_%s", BEAN_NAME_PREFIX, handlerName);
    }

    /**
     * bean 的定于需要用到线程池，就是MessageHandler的构造方法的参数3
     *
     * @return
     */
    private ExecutorService buildExecutor() {
        return new ThreadPoolExecutor(
                1,
                1,
                60,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2)
        );
    }

    /**
     * <h2>构建我们想要注册到 IOC 中的 bean</h2>
     */
    private BeanDefinition buildBeanDefinition(String handlerName) {
        // 方法的参数
        ConstructorArgumentValues values = new ConstructorArgumentValues();
        // 定义的三个属性 就是MessageHandler的构造方法的参数
        values.addIndexedArgumentValue(0, buildBeanName(handlerName));
        values.addIndexedArgumentValue(1, new RuntimeBeanReference(PROCESSOR_BEAN_NAME));// 需要获取到bean的引用
        values.addIndexedArgumentValue(2, buildExecutor());
        // 生成一个新的 Bean 元数据定义
        GenericBeanDefinition definition = newBeanDefinition();
        // 描述信息
        definition.setDescription(buildBeanName(handlerName));
        // 构造参数, 依赖
        definition.setConstructorArgumentValues(values);
        // bean 信息
        definition.setBeanClassName(MessageHandler.class.getName());
        // 设置bean Class
        definition.setBeanClass(MessageHandler.class);

        return definition;
    }
}
