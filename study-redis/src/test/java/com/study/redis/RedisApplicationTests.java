package com.study.redis;

import com.study.redis.function.Function;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class RedisApplicationTests {

    @Resource
    private Function function;

    @Test
    void testReduceStore() {
        function.reduceStore(2);
    }

    @Test
    void testReduceStore2() {
        function.reduceStore2();
    }

    /**
     * 使用redis生成唯一值
     */
    @Test
    void testOnlyNumber() {
        function.onlyNumber();
    }

    @Test
    void testUpateTop10() {
        function.upateTop10();
    }

    /**
     * redis用作排行榜
     */
    @Test
    void testGetTop10() {
        function.getTop10();
    }

    /**
     * 使用redis限流
     */
    @Test
    void testLimit() {
        for (int i = 0; i < 112; i++) {
            Boolean result = function.limit();
            if (!result) {
                //需要限流
                System.out.println("限流了......");
            } else {
                System.out.println("通过 "+i);
            }
        }
    }

    /**
     * redisson 布隆过滤器
     */
    @Test
    void testBloomFilter() {
        function.bloomFilter();
    }

    // 锁可重入
    @Test
    public void lockTest() {
        function.lockTest();
    }

}
