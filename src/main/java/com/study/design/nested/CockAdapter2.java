package com.study.design.nested;

/**
 * @author zzh
 * @date 2024/12/20 15:14
 * @description:
 */
public class CockAdapter2 extends WildCock implements Duck{

    @Override
    public void quack() {
        this.gobble();
    }
}
