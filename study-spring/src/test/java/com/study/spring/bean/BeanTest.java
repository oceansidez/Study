package com.study.spring.bean;

import com.study.spring.ApplicationTest;
import com.study.spring.aop.AopTest;
import com.study.spring.bean.post.CouponHandlerFactory;
import com.study.spring.bean.post.CouponTypeEnum;
import com.study.spring.bean.post.couponhandler.ICouponHandlerService;
import com.study.spring.bean.post.couponhandler.ManJianCouponHandlerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BeanTest extends ApplicationTest {
    @Autowired
    private CouponHandlerFactory couponHandlerFactory;

    // 测试bean后置处理器，容器启动是将bean放入map，后续从map中拿取
    @Test
    public void couponTest() {
        ICouponHandlerService handler = couponHandlerFactory.getHandler(CouponTypeEnum.MAN_JIAN);
        ManJianCouponHandlerServiceImpl manjian = (ManJianCouponHandlerServiceImpl) handler;
        log.info("{}", manjian);
    }

}
