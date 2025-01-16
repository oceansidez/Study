package com.study.base.design.decoration;

/**
 * @author zzh
 * @date 2024/12/20 15:31
 * @description:
 */
public class BlackTea extends Beverage {
    @Override
    String getDescription() {
        return "红茶";
    }

    @Override
    double cost() {
        return 10;
    }
}
