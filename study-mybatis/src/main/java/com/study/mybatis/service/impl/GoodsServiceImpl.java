package com.study.mybatis.service.impl;

import com.study.mybatis.dao.GoodsDao;
import com.study.mybatis.entity.Goods;
import com.study.mybatis.service.GoodsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Goods)表服务实现类
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    // 开启了一级缓存和Transactional会加入同一个连接，导致两个相同的查询走缓存，默认就算是开启了一级缓存也是会查询两遍
//    @Transactional
    @Override
    public List<Goods> queryByPage(Integer startRow, Integer pageSize) {
        List<Goods> list1 = goodsDao.queryByPage(startRow, pageSize);
        System.out.println("查詢1：" + list1 + "\n");
        // 更新操作会刷新缓存
        //  goodsDao.updateStore(1);
        // 此时业务2进行了更改数据库那么下面的结果就不对了
        List<Goods> list2 = goodsDao.queryByPage(startRow, pageSize);
        System.out.println("查詢2：" + list2 + "\n");
        return list2;
    }

    @Override
    public int updateStore(Integer id) {
        return goodsDao.updateStore(id);
    }
}