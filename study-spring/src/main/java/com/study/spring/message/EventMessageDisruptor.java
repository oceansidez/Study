package com.study.spring.message;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.TimeoutException;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.TimeUnit;

/**
 * <h1>event message disruptor 工具</h1>
 */
@Slf4j
public abstract class EventMessageDisruptor implements InitializingBean, DisposableBean {
    /**
     * 事件转换器，用于设置消息内容
     */
    private static final EventTranslatorOneArg<EventMessage, Object> TRANSLATOR = (message, sequence, obj) -> message.setObj(obj);

    protected Disruptor<EventMessage> disruptor;

    private RingBuffer<EventMessage> ringBuffer;

    private static final EventMessageFactory factory = new EventMessageFactory();

    /**
     * <h2>生产者，发布消息</h2>
     *
     * @param obj
     */
    public void onData(Object obj) {
        ringBuffer.publishEvent(TRANSLATOR, obj);
    }

    protected abstract void handleEvent();

    @Override
    public void afterPropertiesSet() throws Exception {
        final int bufferSize = 1024 * 1024;
        disruptor = new Disruptor<>(
                factory,
                bufferSize,
                DaemonThreadFactory.INSTANCE,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());
        // 设置事件处理器
        handleEvent();
        // 异常处理器
        disruptor.setDefaultExceptionHandler(new EventMessageExceptionHandler());
        //启动 disruptor 实现生产和消费
        disruptor.start();
        // 初始化 ringBuffer
        ringBuffer = disruptor.getRingBuffer();
    }

    @Override
    public void destroy() throws Exception {
        try {
            disruptor.shutdown(1, TimeUnit.MINUTES);
        } catch (TimeoutException e) {
            log.error("....");
        }
    }
}
