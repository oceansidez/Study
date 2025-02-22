package com.study.spring.cache.redis.pipeline;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.BatchOptions;
import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisPipeline implements InitializingBean {

    private final RedissonClient redissonClient;

    private BatchOptions batchOptions;
    private BatchOptions batchOptionsNoReturn;
    private BatchOptions batchOptionsExample;

    @Override
    public void afterPropertiesSet() throws Exception {

        batchOptions = BatchOptions.defaults()
                .executionMode(BatchOptions.ExecutionMode.IN_MEMORY);
        batchOptionsNoReturn = BatchOptions.defaults()
                .executionMode(BatchOptions.ExecutionMode.IN_MEMORY).skipResult();
        batchOptionsExample = BatchOptions.defaults()
                // 执行模式
                .executionMode(BatchOptions.ExecutionMode.IN_MEMORY)
                // 告知 Redis 不需要返回结果, 来减少网络流量
                .skipResult()
                .responseTimeout(2, TimeUnit.SECONDS)
                .retryInterval(2, TimeUnit.SECONDS)
                .retryAttempts(4);
    }

    public RBatch createBatch() {
        return redissonClient.createBatch(batchOptions);
    }

    public RBatch createBatch(boolean skipResult) {
        return skipResult ? redissonClient.createBatch(batchOptionsNoReturn)
                : redissonClient.createBatch(batchOptions);
    }

    public BatchResult<?> executeBatch(RBatch batch) {

        try {
            // 异步执行
//            Future<BatchResult<?>> asyncRes = batch.executeAsync();
            return batch.execute();
        } catch (Exception ex) {
            return null;
        }
    }

    public void useBatch() {

        RBatch batch = createBatch();
        batch.getMap("imooc:map:0322").putAllAsync(new HashMap<>());
        batch.getBucket("imooc:bucket:0322").setAsync(new Object());
        // .....
        executeBatch(batch);
    }
}
