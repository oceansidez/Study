package com.study.spring.cache.redis.structure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RHyperLogLog;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <h1>基数统计</h1>
 * SQL distinct
 * hyperloglog 12k
 * 0.81%
 * */
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisHyperLoglog {

    private final RedissonClient redissonClient;

    public void useHyperLogLog() {

        // PFADD, PFCOUNT, PFMERGE

        // 统计日活
        RHyperLogLog<Long> hyperLogLog_0322 =
                redissonClient.getHyperLogLog("r:hyperloglog:0322");
        hyperLogLog_0322.add(1L);
        hyperLogLog_0322.add(10L);
        hyperLogLog_0322.add(100L);
        hyperLogLog_0322.addAll(Arrays.asList(1000L, 10000L));
        long hyperLogLog_0322_count = hyperLogLog_0322.count();

        RHyperLogLog<Long> hyperLogLog_0323 =
                redissonClient.getHyperLogLog("r:hyperloglog:0323");
        hyperLogLog_0322.add(1L);
        hyperLogLog_0322.add(10L);
        hyperLogLog_0322.add(100L);
        hyperLogLog_0322.addAll(Arrays.asList(1000L, 10000L));

        // 统计周活、月活
        hyperLogLog_0323.mergeWith("imooc:hyperloglog:0322");
        long hyperLogLog_0322_0323_count = hyperLogLog_0323.count();
    }
}
