package com.lmax.disruptor.mytest3;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * @Param
 * @Return
 * @Author xuefei
 * @Date 21/06/2017
 * @Version
 */
public class UnderwriteEventPublisher implements Runnable {

    private int ZombieUser = 10;
    private Disruptor<UnderwriteEvent> disruptor;
    private CountDownLatch latch;

    public UnderwriteEventPublisher(CountDownLatch latch, Disruptor<UnderwriteEvent> disruptor) {
        this.disruptor  = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        UnderwriteEventTranslator underwriteEventTranslator = new UnderwriteEventTranslator();
        for (int i = 0; i < ZombieUser; i++) {
            disruptor.publishEvent(underwriteEventTranslator);
            try {
                Thread.currentThread().sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
        System.out.println("生产者写完" + ZombieUser + "个消息");

    }

    class UnderwriteEventTranslator implements EventTranslator<UnderwriteEvent> {

        @Override
        public void translateTo(UnderwriteEvent event, long sequence) {
            generateTradeTransaction(event);
        }

        private UnderwriteEvent generateTradeTransaction(UnderwriteEvent event) {
            int num = (int) (Math.random() * 100);
            event.setUnderwriteRequest("保险产品" + num);
            System.out.println("Thread Id " + Thread.currentThread().getId() + " 写完一个event");
            return event;
        }
    }
}
