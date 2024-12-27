package com.study.design.strategy;

import java.util.LinkedList;
import java.util.List;

/**
 * 主题
 *
 * @author zzh
 * @date 2024/12/20 15:49
 * @description:
 */
public class Subject {
    private List<Observer> observers = new LinkedList<Observer>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }


    /**
     * 通知订阅则
     */
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
