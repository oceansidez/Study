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
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
            throws BeansException {

        String beanName = buildBeanName("qinyi");
        BeanDefinition definition = buildBeanDefinition("qinyi");
        registry.registerBeanDefinition(beanName, definition);
        log.info("自定义BeanDefinition注入到容器成功......");
    }

    /**
     * <h2>生成一个新的 Bean 元数据定义</h2>
     */
    public GenericBeanDefinition newBeanDefinition() {

        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setScope(GenericBeanDefinition.SCOPE_SINGLETON);
        definition.setAbstract(false);
        definition.setLazyInit(false);
        // 不使用自动装配手动装配
        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_NO);
        // 不使用依赖检查
        definition.setDependencyCheck(GenericBeanDefinition.DEPENDENCY_CHECK_NONE);
        // 依赖的bean
        definition.setDependsOn();
        definition.setAutowireCandidate(false);
        definition.setPrimary(false);
        definition.setNonPublicAccessAllowed(true);
        definition.setLenientConstructorResolution(false);
        // 启动时的初始化方法
        definition.setInitMethodName(INIT_METHOD_NAME);
        definition.setDestroyMethodName(DESTROY_METHOD_NAME);
        definition.setSynthetic(true);
        definition.setRole(GenericBeanDefinition.ROLE_APPLICATION);

        return definition;
    }

    private String buildBeanName(String handlerName) {
        return String.format("%s_%s", BEAN_NAME_PREFIX, handlerName);
    }

    private ExecutorService buildExecutor() {
        return new ThreadPoolExecutor(1, 1, 60,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(2));
    }

    /**
     * <h2>构建我们想要注册到 IOC 中的 bean</h2>
     */
    private BeanDefinition buildBeanDefinition(String handlerName) {

        ConstructorArgumentValues values = new ConstructorArgumentValues();
        // 定义的三个属性
        values.addIndexedArgumentValue(0, buildBeanName(handlerName));
        values.addIndexedArgumentValue(1, new RuntimeBeanReference(PROCESSOR_BEAN_NAME));
        values.addIndexedArgumentValue(2, buildExecutor());
        // 生成一个新的 Bean 元数据定义
        GenericBeanDefinition definition = newBeanDefinition();
        // 描述信息
        definition.setDescription(buildBeanName(handlerName));
        // 构造参数, 依赖
        definition.setConstructorArgumentValues(values);
        // bean 信息
        definition.setBeanClassName(MessageHandler.class.getName());
        definition.setBeanClass(MessageHandler.class);

        return definition;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
