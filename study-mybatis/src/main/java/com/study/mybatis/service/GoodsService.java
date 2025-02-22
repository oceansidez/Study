package com.study.mybatis.service;

import com.study.mybatis.entity.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> queryByPage(Integer startRow, Integer pageSize);

    int updateStore(Integer id);

}
