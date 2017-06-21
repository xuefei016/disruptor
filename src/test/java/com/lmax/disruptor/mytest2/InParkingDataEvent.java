package com.lmax.disruptor.mytest2;

/**
 * @Param
 * @Return
 * @Author xuefei
 * @Date 21/06/2017
 * @Version
 */
public class InParkingDataEvent {
    private String carLicense = "";

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }

    public String getCarLicense() {
        return carLicense;
    }
}
