package com.lmax.disruptor.mytest;

import com.lmax.disruptor.EventHandler;

/**
 * @Param
 * @Return
 * @Author xuefei
 * @Date 21/06/2017
 * @Version
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long id = Thread.currentThread().getId();
        System.out.println("thread id:" + id + " event value:" + event.getValue());
        Thread.sleep(5000);
    }
}
