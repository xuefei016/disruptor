package com.lmax.disruptor.mytest;

import com.lmax.disruptor.EventFactory;

/**
 * @Param
 * @Return
 * @Author xuefei
 * @Date 21/06/2017
 * @Version
 */
public class LongEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
