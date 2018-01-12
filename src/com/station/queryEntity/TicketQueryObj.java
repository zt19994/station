package com.station.queryEntity;

import org.apache.commons.lang.StringUtils;

public class TicketQueryObj extends BaseQueryObj {

    private String startStation;
    private String stopStation;
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
        if (StringUtils.isNotBlank(maxTime)) {
            maxTime = maxTime + " 23:59:59";
        }
        this.maxTime = maxTime;
    }

    @Override
    public String toString() {
        return "TicketQueryObj{" +
                "startStation='" + startStation + '\'' +
                ", stopStation='" + stopStation + '\'' +
                ", minTime='" + minTime + '\'' +
                ", maxTime='" + maxTime + '\'' +
                '}';
    }
}
