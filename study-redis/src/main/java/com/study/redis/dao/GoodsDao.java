package com.study.redis.dao;

import com.study.redis.entity.Goods;

/**
 * (Goods)表数据库访问层
 *
 */
public interface GoodsDao {

    Goods queryById(Integer id);

    int updateStore(Integer id);

}