package com.study.spring.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CycleA {

    /**
     * <h2>构造方法循环依赖,spring不能解决，可以使用懒加载解决</h2>
     */
    public CycleA(CycleB b) {

    }

//    /** field 属性注入循环依赖, IOC 容器可能会解决掉（单例才能解决） */
//    @Autowired
//    private CycleB b;
}
