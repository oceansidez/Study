package com.study.spring.cache.redis.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.transaction.TransactionException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * redis 事务，很少用
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisTransaction {

    private final RedissonClient redissonClient;

    /**
     * <h2>使用事务</h2>
     * */
    public void useTransaction() {

        TransactionOptions options = TransactionOptions.defaults()
                .responseTimeout(3, TimeUnit.SECONDS)
                .retryInterval(2, TimeUnit.SECONDS)
                .retryAttempts(3)
                .timeout(5, TimeUnit.SECONDS);

        RTransaction transaction = redissonClient.createTransaction(options);
        RMap<String, String> map = transaction.getMap("imooc:map:0322");
        map.put("imooc", "qinyi");
        String value = map.get("kb");
        RSet<String> set = transaction.getSet("imooc:set:0322");
        set.add(value);

        try {
            transaction.commit();
        } catch (TransactionException ex) {
            transaction.rollback();
        }
    }
}
