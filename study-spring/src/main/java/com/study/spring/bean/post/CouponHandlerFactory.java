package com.study.spring.bean.post;

import com.study.spring.bean.post.couponhandler.ICouponHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>coupon 处理器工厂类</h1>
 * 通过工厂模式和BeanPostProcessor实现通过指定类型就能获取指定的service
 * <p>
 * BeanPostProcessor 可以针对bean做一些前置处理和聚合，还能分析bean、处理等如记录初始化时间，安全风险检查等
 */
@Slf4j
@Component
public class CouponHandlerFactory implements BeanPostProcessor {

    private static final Map<CouponTypeEnum, ICouponHandlerService> serviceMap = new ConcurrentHashMap<>();

    /**
     * 初始化方法前执行，此时已经生成了对于的实例
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        // 只处理优惠券的service
        if (bean instanceof ICouponHandlerService) {
            // 获取对象运行时对象类
            Class<?> clazz = bean.getClass();
            // 获取自定义的注解
            CouponHandlerProcessor annotation = clazz.getAnnotation(CouponHandlerProcessor.class);
            // TODO 是不是可以对 bean 进行校验，如没加上注解可以抛出异常等待
            // 绑定对应关系
            serviceMap.put(annotation.value(), (ICouponHandlerService) bean);
            log.info("添加serviceMap{}成功....",annotation.value());
        }

        return bean;
    }

    /**
     * <h2>根据优惠券类型获取优惠券处理器</h2>
     */
    public ICouponHandlerService getHandler(CouponTypeEnum type) {
        return serviceMap.get(type);
    }
}
