package com.study.mybatis.dao;

import com.study.mybatis.entity.Goods;

import java.util.List;

/**
 * (Goods)表数据库访问层
 *
 */
public interface GoodsDao {

    List<Goods> queryByPage(Integer startRow, Integer pageSize);

    int updateStore(Integer id);
}