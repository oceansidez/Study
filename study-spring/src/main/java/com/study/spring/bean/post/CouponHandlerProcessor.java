package com.study.spring.bean.post;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CouponHandlerProcessor {

    /** 优惠券类型 */
    CouponTypeEnum value();
}
