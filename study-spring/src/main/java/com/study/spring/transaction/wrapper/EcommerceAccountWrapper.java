package com.study.spring.transaction.wrapper;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;

import com.study.spring.transaction.entity.EcommerceAccount;
import com.study.spring.transaction.mapper.EcommerceAccountMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <h1>EcommerceAccount Mapper Wrapper</h1>
 * 用于封装数据库操作
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EcommerceAccountWrapper {

    @Getter
    private final EcommerceAccountMapper mapper;

    public LambdaQueryChainWrapper<EcommerceAccount> getQueryChainWrapper() {
        return new LambdaQueryChainWrapper<>(this.mapper);
    }

    /**
     * <h2>select * from t_ecommerce_account where username = ?</h2>
     */
    public EcommerceAccount findAccountByUsername(String username) {

        return getQueryChainWrapper()
                .eq(EcommerceAccount::getUserId, 1L)
//                .eq(EcommerceAccount::getUsername, username)
                .one();
    }
}
