package com.study.base.design.nested;

/**
 * @author zzh
 * @date 2024/12/20 15:12
 * @description:
 */
public class Mian {
    public static void main(String[] args) {
        // 有一只野鸡
        Cock wildCock = new WildCock();
        // 成功将野鸡适配成鸭
        Duck duck = new CockAdapter(wildCock);
        Duck duck2 = new CockAdapter2();
        duck.fly();
        duck.quack();

        duck2.fly();
        duck2.quack();
    }
}
