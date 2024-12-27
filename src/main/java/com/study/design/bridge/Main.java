package com.study.design.bridge;

/**
 * @author zzh
 * @date 2024/12/20 15:22
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(10, new GreenPen());
        circle.draw();
        new Rectangle(4,8,new BluePen()).draw();
    }
}
