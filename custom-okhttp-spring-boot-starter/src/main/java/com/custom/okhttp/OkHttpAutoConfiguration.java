package com.custom.okhttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>OkHttp 客户端的自动配置</h1>
 */
@Slf4j
@Configuration
public class OkHttpAutoConfiguration {

    @Bean
//    @ConditionalOnProperty(prefix = "custom", value = "okhttp", matchIfMissing = true)
    @ConditionalOnProperty(prefix = "custom", name = "okhttp", havingValue = "auto")
    public OkHttpClient okHttpClient() {
        // 可以根据业务需要, 添加对 okhttp 的配置
        log.info("已完成 OkHttpClient 的自动装配.... ");
        return new OkHttpClient();
    }
}
