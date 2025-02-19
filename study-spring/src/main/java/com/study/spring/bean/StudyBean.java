package com.study.spring.bean;

import com.study.spring.service.IAccountService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


/**
 * <h1>bean 的生命周期</h1>
 */
@Slf4j
@Component
public class StudyBean implements InitializingBean, BeanPostProcessor {

    /**
     * <h2>在依赖注入完成之后就会执行</h2>
     */
    @PostConstruct
    public void init() {
        log.info("在依赖注入完成之后就会执行...");
    }

    /**
     * <h2>在 BeanFactory 完成属性设置之后执行的, 实现 InitializingBean 接口</h2>
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("完成属性设置之后执行...");
    }

    /**
     * <h2>bean 初始化前置方法,对所有的bean生效，需要加上判断</h2>
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof IAccountService) {
            // todo 可以争对IAccountService 进行操作
            log.info("IAccountService初始化前置方法执行....");
        }
        // 需要返回bean不然会报错
        return bean;
    }

    /**
     * <h2>bean 初始化后置方法</h2>
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof IAccountService) {
            log.info("IAccountService初始化后置方法执行....");
        }
        return bean;
    }

    /**
     * <h2>销毁 bean 的时候回调</h2>
     */
    @PreDestroy
    public void destroy() {
        log.info("销毁 bean 的时候回调....");
    }
}
