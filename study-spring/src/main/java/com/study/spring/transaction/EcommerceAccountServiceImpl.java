package com.study.spring.transaction;

import com.study.spring.transaction.entity.EcommerceAccount;
import com.study.spring.transaction.entity.EcommerceAccountVip;
import com.study.spring.transaction.wrapper.EcommerceAccountVipWrapper;
import com.study.spring.transaction.wrapper.EcommerceAccountWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

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

        // 方式1 执行事务没有返回值
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

        // 方式2 执行事务可以有一个返回值
        transactionTemplate.execute((tx) -> {
            try {
                EcommerceAccount account = EcommerceAccount.builder()
                        .userId(1L)
                        .build();
                EcommerceAccountVip accountVip = EcommerceAccountVip.builder()
                        .userId(1L)
                        .build();

                // 执行插入操作
                ecommerceAccountWrapper.getMapper().insert(account);
                ecommerceAccountVipWrapper.getMapper().insert(accountVip);

                // 如果所有操作成功，返回 true
                return true;
            } catch (Exception e) {
                // 捕获异常并打印日志
                log.error("Error occurred while inserting records: {}", e.getMessage());

                // 在出现异常时回滚事务，默认情况下 Spring 会自动回滚
                tx.setRollbackOnly();

                // 可以返回 false 或其他值，表示事务失败
                return false;
            }
        });

    }

    /**
     * 隔离级别 isolation
     * Isolation.DEFAULT：这是默认设置，表示使用底层数据库的默认隔离级别。通常情况下，DEFAULT 会映射为 READ_COMMITTED。
     * Isolation.READ_UNCOMMITTED：允许读取未提交的数据，可能会读取到脏数据（其他事务未提交的数据）。通常不推荐使用。
     * Isolation.READ_COMMITTED：只能读取已提交的数据，避免脏数据，但可能会产生不可重复读。
     * Isolation.REPEATABLE_READ：保证读取到的每一行数据在事务期间都不会变化，避免不可重复读，但可能会产生幻读（其他事务插入数据）。
     * Isolation.SERIALIZABLE：最高的隔离级别，确保事务串行执行，避免所有并发问题（脏读、不可重复读和幻读），性能较差。
     * <p>
     * 传播行为 propagation
     * Propagation.REQUIRED：如果当前方法已有事务，则加入当前事务；如果没有事务，创建一个新的事务（这是最常用的行为）。
     * Propagation.REQUIRES_NEW：无论当前方法是否有事务，都会开启一个新的事务，并挂起当前事务。
     * Propagation.SUPPORTS：如果当前方法已经存在事务，则加入当前事务；如果没有事务，当前方法可以在没有事务的情况下执行。
     * Propagation.NOT_SUPPORTED：无论当前方法是否有事务，都不执行事务，当前方法会在非事务环境下执行。
     * Propagation.MANDATORY：当前方法必须在一个事务内执行，如果没有事务，抛出异常。
     * Propagation.NEVER：当前方法必须在没有事务的情况下执行，如果存在事务，抛出异常。
     * Propagation.NOSUPPORT：当前方法不需要事务，如果有事务则挂起当前事务。
     * <p>
     * 运行时异常 rollbackFor（RuntimeException）和 错误（Error）才会导致事务回滚
     * <p>
     * 超时时间 timeout 事务如果执行超过 30 秒（30000 毫秒），就会自动回滚。
     */
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

    /**
     * 事务失效场景
     */
    /**
     * 1.在事务中进行了捕获
     */
    @Transactional(rollbackFor = Exception.class)
    public void invalid1() throws Exception {
        try {
            // 业务代码
        } catch (Exception ex) {

        }
    }

    /**
     * 2.默认只能回滚runtime的，需要标记(rollbackFor = Exception.class)
     */
    @Transactional
    public void invalid2() throws Exception {

        // 业务代码
        throw new SQLException();
    }

    /**
     * 3.使用了private修饰
     */
    @Transactional(rollbackFor = Exception.class)
    private void invalid3() throws Exception {

    }

    /**
     * 4. 在同一个类中调用， invalid4_2的事务也不会生效，需要调用aop的方法才行
     */
    public void invalid4_1() {
        invalid4_2();
    }

    @Transactional(rollbackFor = Exception.class)
    public void invalid4_2() {
        // ...
    }

}
