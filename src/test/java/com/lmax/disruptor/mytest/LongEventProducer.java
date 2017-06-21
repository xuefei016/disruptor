package com.lmax.disruptor.mytest;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @Param
 * @Return
 * @Author xuefei
 * @Date 21/06/2017
 * @Version
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        System.out.println("producer thread id:" + Thread.currentThread().getId());
        long sequence = ringBuffer.next();
        try {
            LongEvent longEvent = ringBuffer.get(sequence);
            longEvent.setValue(bb.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }

    }
}
