package com.day_28.station.queryEntity;

public class TicketQueryObj extends BaseQueryObj {

    private String startStation;
    private String stopStation;

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


}
