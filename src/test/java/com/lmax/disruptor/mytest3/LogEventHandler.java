package com.lmax.disruptor.mytest3;

import com.lmax.disruptor.EventHandler;

/**
 * @Param
 * @Return
 * @Author xuefei
 * @Date 21/06/2017
 * @Version
 */
public class LogEventHandler implements EventHandler<UnderwriteEvent> {
    @Override
    public void onEvent(UnderwriteEvent event, long sequence, boolean endOfBatch) throws Exception {
        long threadId = Thread.currentThread().getId();
        String underwriteRequest = event.getUnderwriteRequest();
        System.out.println(String.format("Thread Id %s send underwrite request  %s into kafaka ...", threadId, underwriteRequest));
    }
}
