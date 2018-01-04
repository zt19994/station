package com.day_28.station.queryEntity;

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
