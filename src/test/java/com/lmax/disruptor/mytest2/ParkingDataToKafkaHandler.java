package com.lmax.disruptor.mytest2;

import com.lmax.disruptor.EventHandler;

/**
 * @Param
 * @Return
 * @Author xuefei
 * @Date 21/06/2017
 * @Version
 */
public class ParkingDataToKafkaHandler implements EventHandler<InParkingDataEvent> {

    @Override
    public void onEvent(InParkingDataEvent event, long sequence,
                        boolean endOfBatch) throws Exception {
        long threadId = Thread.currentThread().getId();
        String carLicense = event.getCarLicense();
        System.out.println(String.format("Thread Id %s send %s in plaza messsage to kafka...", threadId, carLicense));
    }
}

