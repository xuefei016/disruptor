package com.lmax.disruptor.mytest;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Param
 * @Return
 * @Author xuefei
 * @Date 21/06/2017
 * @Version
 */
public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main thread id:" + Thread.currentThread().getId());
        Executor excecutor = Executors.newCachedThreadPool();
        LongEventFactory longEventFactory = new LongEventFactory();
        int bufferSize = 2;
        Disruptor disruptor = new Disruptor<LongEvent>(longEventFactory, bufferSize, excecutor);
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();
        RingBuffer ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }

}
