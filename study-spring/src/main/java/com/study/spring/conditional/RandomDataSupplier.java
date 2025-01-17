package com.study.spring.conditional;

import java.util.function.Supplier;

/**
 * <h1>随机数生成器</h1>
 * */
public class RandomDataSupplier<T> {

    private final Supplier<T> rand;

    public RandomDataSupplier(Supplier<T> rand) {
        this.rand = rand;
    }

    public T rand() {
        return rand.get();
    }
}
