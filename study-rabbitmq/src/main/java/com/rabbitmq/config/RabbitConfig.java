package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直接交换机
 */
@Configuration
public class RabbitConfig {

    public static final String DIRECT_EXCHANGE_NAME = "direct.exchange";

    public static final String DIRECT_QUEUE_NAME = "direct.queue";

    public static final String DIRECT_ROUTING_KEY = "info";

    /**
     * 声明创建一个DirectExchange交换机
     *
     * @return
     */
    @Bean("directExchange1")
    public DirectExchange directExchange() {
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    /**
     * 声明创建一个队列
     *
     * @return
     */
    @Bean("directQueue1")
    public Queue directQueue() {
        return QueueBuilder.durable(DIRECT_QUEUE_NAME).build();
    }

    /**
     * 把 队列 和 交换机 绑定
     *
     * @param directQueue
     * @param directExchange
     * @return
     */
    @Bean
    public Binding directBinding(@Qualifier("directQueue1") Queue directQueue, @Qualifier("directExchange1") DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with(DIRECT_ROUTING_KEY);
    }
}
