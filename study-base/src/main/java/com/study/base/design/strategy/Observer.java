package com.study.base.design.strategy;

/**
 * 观察者
 *
 * @author zzh
 * @date 2024/12/20 15:50
 * @description:
 */
public abstract class Observer {
    protected Subject subject;

    /**
     * 更新通知
     */
    public abstract void update();
}
