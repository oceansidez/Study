package com.study.redis.service.impl;

import com.study.redis.dao.GoodsDao;
import com.study.redis.entity.Goods;
import com.study.redis.service.GoodsService;
import jakarta.annotation.Resource;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

/**
 * (Goods)表服务实现类
 * 使用redisson分布式锁
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private RedissonClient redissonClient;

    @Override
    public int reduceStore(Integer id) {
        int update = 0;
        RLock lock = redissonClient.getLock("lock:reduce:store");

        try {
            // 上锁
            lock.lock();

            /*try {
                Thread.sleep(1000000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/

            // 业务代码 start
            Goods goods = goodsDao.queryById(id);
            Integer store = goods.getStore();
            if (store > 0) {
                update = goodsDao.updateStore(id);
                if (update > 0) {
                    System.out.println("减库存成功，可以操作业务");
                }
            }
            //业务代码 end

        } finally {
            // 自己的锁自己释放
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

        return update;
    }
}