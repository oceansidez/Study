package com.study.springboot.service;

import com.study.springboot.entity.GoodsItem;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.Future;

public interface GoodsItemService {

    public List<GoodsItem> getGoodsItems(Long uid);

    @Async
    public Future<List<GoodsItem>> getGoodsItems2(Long uid);
}
