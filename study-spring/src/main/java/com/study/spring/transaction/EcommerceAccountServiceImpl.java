package com.study.spring.transaction;

import com.study.spring.entity.EcommerceAccount;
import com.study.spring.entity.EcommerceAccountVip;
import com.study.spring.wrapper.EcommerceAccountVipWrapper;
import com.study.spring.wrapper.EcommerceAccountWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 编程式事务、声明式事务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EcommerceAccountServiceImpl {

    /**
     * 模板事务
     */
    private final TransactionTemplate transactionTemplate;

    private final EcommerceAccountWrapper ecommerceAccountWrapper;
    private final EcommerceAccountVipWrapper ecommerceAccountVipWrapper;

    /**
     * <h2>使用编程式事务</h2>
     */
    public void addAccountUserTransactionTemplate() {

        // 执行事务没有返回值
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    EcommerceAccount account = EcommerceAccount.builder()
                            .userId(1L)
                            .build();
                    EcommerceAccountVip accountVip = EcommerceAccountVip.builder()
                            .userId(1L)
                            .build();
                    ecommerceAccountWrapper.getMapper().insert(account);
                    ecommerceAccountVipWrapper.getMapper().insert(accountVip);
                    // 抛出异常
                    throw new Exception();
                } catch (Exception ex) {
                    // 设置回滚
                    status.setRollbackOnly();
                }
            }
        });

        // 执行事务可以有一个返回值
        transactionTemplate.execute((tx) -> {
            EcommerceAccount account = EcommerceAccount.builder()
                    .userId(1L)
                    .build();
            EcommerceAccountVip accountVip = EcommerceAccountVip.builder()
                    .userId(1L)
                    .build();
            ecommerceAccountWrapper.getMapper().insert(account);
            ecommerceAccountVipWrapper.getMapper().insert(accountVip);
            return true;
        });
    }

    @Transactional(isolation = Isolation.DEFAULT,
            propagation = Propagation.REQUIRED, rollbackFor = Exception.class,
            timeout = 30000)
    public void demo() {
        // 业务代码
    }

    @Transactional(rollbackFor = Exception.class)
    public void addAccountUserAnnotation() {
        // 业务代码
    }

}
