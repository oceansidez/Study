package com.study.spring.cache.redis.problem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisCachePassThrough {

    private final RedissonClient redissonClient;

    public void solution() {

        String dataKey = "r:expire:0322";
        String cacheData = (String) redissonClient.getBucket(dataKey).get();

        boolean isEmptyData = fakeCheckLogic();

        if (isEmptyData) {
            String fakeData = NumberUtils.INTEGER_MINUS_ONE.toString();
            redissonClient.getBucket(dataKey).setAsync(fakeData, 1, TimeUnit.DAYS);
        } else {
            //
        }
    }

    private boolean fakeCheckLogic() {
        return RandomUtils.nextInt(10, 100) > 50;
    }
}
