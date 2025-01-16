package com.study.spring.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CycleB {

    public CycleB(CycleA a) {

    }

//    @Autowired
//    private ImoocCycleA a;
}
