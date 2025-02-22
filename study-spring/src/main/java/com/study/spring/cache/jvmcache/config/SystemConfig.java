package com.study.spring.cache.jvmcache.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <h1>系统配置</h1>
 * */
@Data
@Builder
@AllArgsConstructor
public class SystemConfig {

    private Integer configId;
    private String configName;
    private String limit;
}
