package com.day_28.station.queryEntity;

/**
 * 订单查询对象
 */
public class OrderQueryObj extends BaseQueryObj {
    private String startStation;
    private String stopStation;
    private String userName;

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getStopStation() {
        return stopStation;
    }

    public void setStopStation(String stopStation) {
        this.stopStation = stopStation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
