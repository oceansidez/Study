package com.study.base.design.strategy;

/**
 * @author zzh
 * @date 2024/12/20 15:55
 * @description:
 */
public class BinaryObserver extends Observer {

    // 在构造方法中进行订阅主题
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void update() {
        String result = Integer.toBinaryString(subject.getState());
        System.out.println("订阅的数据发生变化，新的数据处理为二进制值为：" + result);
    }
}
