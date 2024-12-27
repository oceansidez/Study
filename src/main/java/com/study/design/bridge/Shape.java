package com.study.design.bridge;

/**
 * @author zzh
 * @date 2024/12/20 15:18
 * @description:
 */
public  abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
