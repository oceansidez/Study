package com.study.base.design.strategy;

/**
 * @author zzh
 * @date 2024/12/20 16:01
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new HexaObserver(subject);
        subject.setState(11);
    }
}
