package com.study.design.decoration;

/**
 * @author zzh
 * @date 2024/12/20 15:34
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        Beverage greenTea = new GreenTea();
        greenTea= new Lemon(greenTea);
        greenTea = new Mango(greenTea);
        System.out.println(greenTea.getDescription() + " 价格：￥" + greenTea.cost());


        Beverage greenTea2 = new Lemon(new Lemon(new Mango(greenTea)));
        System.out.println(greenTea2.getDescription() + " 价格：￥" + greenTea2.cost());
    }
}
