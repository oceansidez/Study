package com.study.design.decoration;

/**
 * 林蒙
 *
 * @author zzh
 * @date 2024/12/20 15:32
 * @description:
 */
public class Lemon extends Condiment {
    private Beverage bevarage;

    public Lemon(Beverage bevarage) {
        this.bevarage = bevarage;
    }

    @Override
    String getDescription() {
        return bevarage.getDescription() + ", 加柠檬";
    }

    @Override
    double cost() {
        return bevarage.cost() + 2; // 加柠檬需要 2 元
    }
}
