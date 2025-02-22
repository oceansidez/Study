package com.study.spring.cache.redis.structure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.BitSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisBitMap {

    private final RedissonClient redissonClient;

    public void useBitMap() {

        long userId = 1L;
        RBitSet dailyActiveUserBitMap_0321 = redissonClient.getBitSet("r:bitmap:0321");

        // 基础使用方法
        dailyActiveUserBitMap_0321.set(userId);
        dailyActiveUserBitMap_0321.clear(userId);

        // 获取位图上被设置为 true 的个数
        long trueCount = dailyActiveUserBitMap_0321.cardinality();
        boolean isTure = dailyActiveUserBitMap_0321.get(userId);

        // 多个 bitmap 可以做 and、or、xor 操作
        RBitSet dailyActiveUserBitMap_0322 = redissonClient.getBitSet("r:bitmap:0322");
        // 转换为 java 数据结构
        BitSet dailyActiveUserBitMap_0321_bitset = dailyActiveUserBitMap_0321.asBitSet();
        BitSet dailyActiveUserBitMap_0322_bitset = dailyActiveUserBitMap_0322.asBitSet();

        dailyActiveUserBitMap_0321_bitset.and(dailyActiveUserBitMap_0322_bitset);
        int statAndCount = dailyActiveUserBitMap_0321_bitset.cardinality();
    }
}
