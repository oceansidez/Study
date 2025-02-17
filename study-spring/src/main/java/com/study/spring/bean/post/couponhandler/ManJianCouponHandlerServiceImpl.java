package com.study.spring.bean.post.couponhandler;


import com.study.spring.bean.post.CouponHandlerProcessor;
import com.study.spring.bean.post.CouponTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@CouponHandlerProcessor(CouponTypeEnum.MAN_JIAN)
public class ManJianCouponHandlerServiceImpl implements ICouponHandlerService {

    @Override
    public void process(String coupon) {
        log.info("ManJian....");
    }
}
