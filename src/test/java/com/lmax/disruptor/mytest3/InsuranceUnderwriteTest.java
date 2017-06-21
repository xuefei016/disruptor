package com.lmax.disruptor.mytest3;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Param
 * @Return
 * @Author xuefei
 * @Date 21/06/2017
 * @Version
 */
public class InsuranceUnderwriteTest {
    public static void main(String[] args) throws InterruptedException {
        long beginTime = System.currentTimeMillis();

        int bufferSize = 1024;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        //构造缓冲区与事件生成
        Disruptor<UnderwriteEvent> disruptor = new Disruptor<UnderwriteEvent>(new EventFactory<UnderwriteEvent>() {
            @Override
            public UnderwriteEvent newInstance() {
                return new UnderwriteEvent();
            }
        }, bufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());

        EventHandlerGroup<UnderwriteEvent> handlerGroup = disruptor
                .handleEventsWith(new ValidateEventHandler(), new LogEventHandler());

        CallFuckinPCEventHandler callFuckinPCEventHandler = new CallFuckinPCEventHandler();
        handlerGroup.then(callFuckinPCEventHandler);

        disruptor.start();
        CountDownLatch latch = new CountDownLatch(1);
        executor.submit(new UnderwriteEventPublisher(latch, disruptor));
        latch.await();
        disruptor.shutdown();
        executor.shutdown();

        System.out.println("总耗时:" + (System.currentTimeMillis() - beginTime));
    }
}
