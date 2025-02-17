package com.study.spring.transaction.wrapper;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;

import com.study.spring.transaction.entity.EcommerceAccountVip;
import com.study.spring.transaction.mapper.EcommerceAccountVipMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <h1>EcommerceAccountVip Mapper Wrapper</h1>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EcommerceAccountVipWrapper {

    @Getter
    private final EcommerceAccountVipMapper mapper;

    public LambdaQueryChainWrapper<EcommerceAccountVip> getQueryChainWrapper() {
        return new LambdaQueryChainWrapper<>(this.mapper);
    }
}
