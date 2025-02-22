package com.study.redis.function;

import com.study.redis.service.GoodsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Slf4j
@Component
public class Function {

    @Resource
    private GoodsService goodsService;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 减少商品
     */
    public void reduceStore(Integer id) {
        goodsService.reduceStore(2);
    }

    /**
     * 并发情况下进行商品购物，测试分布式锁
     */
    public void reduceStore2() {
        for (int i = 0; i < 100; i++) {
            threadPoolTaskExecutor.submit(() -> {
                goodsService.reduceStore(2);
            });
        }
    }

    /**
     * 使用redis生成唯一值
     */
    public void onlyNumber() {
        for (int i = 0; i < 100; i++) {
            threadPoolTaskExecutor.submit(() -> {
                //生成唯一订单号
                Long orderNum = stringRedisTemplate.opsForHash().increment("redis:only:number", "order", 1);
                String orderNo = "OD" + System.currentTimeMillis() + "" + orderNum;
                System.out.println("订单号：" + orderNo);

                //生成唯一支付流水号
                Long payNum = stringRedisTemplate.opsForHash().increment("redis:only:number", "pay", 1);
                String payNo = "PY" + System.currentTimeMillis() + "" + payNum;
                System.out.println("流水号：" + payNo);

                System.out.println("---------------------------");
            });
        }
    }

    public void setTop10() {
        stringRedisTemplate.opsForZSet().add("redis:top10", "15311115285", 3669);
        stringRedisTemplate.opsForZSet().add("redis:top10", "19311117187", 3458);
        stringRedisTemplate.opsForZSet().add("redis:top10", "18811118883", 3065);
        stringRedisTemplate.opsForZSet().add("redis:top10", "15511111627", 2859);
        stringRedisTemplate.opsForZSet().add("redis:top10", "18211115078", 2669);
        stringRedisTemplate.opsForZSet().add("redis:top10", "18611119337", 2199);
        stringRedisTemplate.opsForZSet().add("redis:top10", "13511116265", 2119);
        stringRedisTemplate.opsForZSet().add("redis:top10", "13211115165", 2059);
        stringRedisTemplate.opsForZSet().add("redis:top10", "13511113545", 1919);
        stringRedisTemplate.opsForZSet().add("redis:top10", "17311119872", 1901);
    }

    public void upateTop10() {
        stringRedisTemplate.opsForZSet().incrementScore("redis:top10", "19311117187", 513);
        stringRedisTemplate.opsForZSet().incrementScore("redis:top10", "13511116265", 219);
    }

    /**
     * redis用作排行榜
     */
    public void getTop10() {
        setTop10();
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet
                = stringRedisTemplate.opsForZSet().reverseRangeWithScores("redis:top10", 0, 4);
        int i = 1;
        for (ZSetOperations.TypedTuple<String> stringTypedTuple : typedTupleSet) {
            System.out.println(i + "   " + stringTypedTuple.getValue() + "   " + stringTypedTuple.getScore().intValue());
            i++;
        }
    }

    /**
     * 使用redis限流
     */
    public void limit() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limit.lua")));

        for (int i = 0; i < 112; i++) {
            // 相当于每秒一个key
            String key = "limit:" + System.currentTimeMillis() / 1000; // limit:1705664721
            // 限流100并发
            Long result = stringRedisTemplate.execute(redisScript, Arrays.asList(key), String.valueOf(100));
            if (result == 0) {
                //需要限流
                System.out.println("限流了......");
            }
        }
    }

    /**
     * redisson 布隆过滤器
     */
    public void bloomFilter() {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        // 预计插入的元素数量：100967256 允许的误判率：0.01（1%）
        bloomFilter.tryInit(100967256, 0.01);

        for (int i = 0; i < 1000; i++) {
            boolean added = bloomFilter.add("布隆" + i);
            System.out.println("布隆" + i + "-->是否添加成功：" + added);
        }
        System.out.println("--------------------------------------");

        for (int i = 0; i < 1000; i++) {
            System.out.println("'布隆'" + i + "是否存在：" + bloomFilter.contains("布隆" + i));
        }

        System.out.println("--------------------------------------");
        System.out.println("'布隆998'是否存在：" + bloomFilter.contains("布隆998"));
        System.out.println("预计插入数量：" + bloomFilter.getExpectedInsertions());
        System.out.println("误判率：" + bloomFilter.getFalseProbability());
        System.out.println("hash函数的个数：" + bloomFilter.getHashIterations());
        System.out.println("添加元素的个数：" + bloomFilter.count());
        System.out.println("数组的长度：" + bloomFilter.getSize());
    }

    // 锁可重入
    public void lockTest() {
        RLock lock = redissonClient.getLock("lock:writeData");
        writeData(lock, 3);
    }

    public void writeData(RLock lock, int count) {
        try {
            lock.lock(); // 加锁
            System.out.println("Lock acquired, count: " + count);
            if (count > 0) {
                writeData(lock, --count);
            } else {
                return;
            }
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock(); // 释放锁
                System.out.println("Lock released, count: " + count);
            }
        }
    }
}
