package com.study.spring.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CycleB {

    public CycleB(@Lazy CycleA a) {

    }

//    @Autowired
//    private CycleA a;
}
