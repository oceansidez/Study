package com.study.springboot.service.impl;


import com.study.springboot.entity.GoodsItem;
import com.study.springboot.execute.AsyncResult;
import com.study.springboot.service.GoodsItemService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class GoodsItemServiceImpl implements GoodsItemService {

    public List<GoodsItem> getGoodsItems(Long uid) {
        System.out.println("getGoodsItems-->" + Thread.currentThread().getName());
        try {
            Thread.sleep(280);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(new GoodsItem());
    }

    @Async
    public Future<List<GoodsItem>> getGoodsItems2(Long uid) {
        System.out.println("getGoodsItems-->" + Thread.currentThread().getName());
        try {
            Thread.sleep(280);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult(Arrays.asList(new GoodsItem()));
    }
}
