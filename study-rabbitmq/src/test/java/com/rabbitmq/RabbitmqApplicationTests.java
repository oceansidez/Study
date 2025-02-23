package com.rabbitmq;

import com.rabbitmq.sender.MessageDLXSender;
import com.rabbitmq.sender.MessageSender;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {

    @Resource
    private MessageSender messageSender;

    /**
     * 发送消息到 direct.exchange
     * @throws Exception
     */
    @Test
    void contextLoads() throws Exception {
        messageSender.sendMessage();
    }

    /**
     * 延迟队列
     * @throws Exception
     */
    @Test
    void testTTL() throws Exception {
        //消息属性设置
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("10000"); //设置过期时间

        //消息内容
        String msg = "Hello, RabbitMQ";
        //创建一个消息
        Message message = new Message(msg.getBytes(), messageProperties);

        //发送消息
        messageSender.sendMessage2(message);
        System.out.println("消息发送完毕");
    }


    /**
     * 测试死信队列
     */
    @Resource
    private MessageDLXSender messageDLXSender;
    /**
     * 发送消息到 direct.exchange
     * @throws Exception
     */
    @Test
    void contextLoads2() throws Exception {
        for (int i = 0; i < 100; i++) {
            messageDLXSender.sendMessage();
        }
    }

}
