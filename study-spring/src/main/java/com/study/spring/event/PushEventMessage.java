package com.study.spring.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * <h1>推送时间消息定义</h1>
 */
@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class PushEventMessage {

    private Long userId;
    private Long pushTime;
    private String content;
}
