package com.study.spring.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CycleB {

    /**
     * 可以使用懒加载解决
     *
     * @param a
     */
    public CycleB(@Lazy CycleA a) {

    }

//    @Autowired
//    private CycleA a;
}
