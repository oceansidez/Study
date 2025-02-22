package com.study.spring.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;


/**
 * <h1>事件消息，事件数据模型</h1>
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EventMessage implements Serializable {
    private Object obj;
}