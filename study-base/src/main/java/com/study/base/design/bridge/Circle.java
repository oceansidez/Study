package com.study.base.design.bridge;

/**
 * @author zzh
 * @date 2024/12/20 15:19
 * @description:
 */
public class Circle extends Shape {
    private int radius;

    public Circle(int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.draw(radius, 0, 0);
    }
}
