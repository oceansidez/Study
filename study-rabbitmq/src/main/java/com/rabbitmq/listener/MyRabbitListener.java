package com.rabbitmq.listener;

import com.rabbitmq.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.config.RabbitConfig2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class MyRabbitListener {

    @RabbitListener(queues = {RabbitConfig.DIRECT_QUEUE_NAME})
    public void onMessage(String msg, @Headers Map<String, Object> header, Message message, Channel channel) {
        try {
            System.out.println("[RabbitListener]接收到的消息: " + msg);

            //处理业务

            //业务处理成功，手动确认消息 ,只确认当前消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            // 发生异常时业务处理失效，不确认消息，
            //  requeue:true 重新入队，这样又可以重新消费 false:消息会被丢弃或路由到死信队列
            // channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

            //拒绝消息 明确知道某个消息不应该被处理时
            // channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("-------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    AtomicInteger atomicInteger1 = new AtomicInteger(0);
    AtomicInteger atomicInteger2 = new AtomicInteger(0);

    // 处理普通队列消息
    @RabbitListener(queues = {RabbitConfig2.QUEUE_NAME})
    public void OnMessage2(String msg, @Headers Map<String, Object> head, Message message, Channel channel) {
        try {
            int i = atomicInteger1.incrementAndGet();
            System.out.println("[RabbitListener]接收到的消息1: " + i + " " + msg);
            if (i % 3 == 0) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
            System.out.println("-------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 处理死信队列的消息
    @RabbitListener(queues = {RabbitConfig2.QUEUE_NAME_DLX})
    public void OnMessage3(String msg, @Headers Map<String, Object> head, Message message, Channel channel) {
        try {
            int i = atomicInteger2.incrementAndGet();
            System.out.println("[RabbitListener]接收到的消息2: " + i + " " + msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("-------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
