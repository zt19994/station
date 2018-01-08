package com.day_28.station.queryEntity;

import org.apache.commons.lang.StringUtils;

/**
 * 订单查询对象
 */
public class OrderQueryObj extends BaseQueryObj {
    private String startStation;
    private String stopStation;
    private String userName;
    private String minTime;
    private String maxTime;

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

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        //最大时间需要到23:59:59
        if (StringUtils.isNotBlank(maxTime)){
            maxTime = maxTime + " 23:59:59";
        }
        this.maxTime = maxTime;
    }
}
