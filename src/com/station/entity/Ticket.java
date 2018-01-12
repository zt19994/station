package com.station.entity;

import com.station.commons.map.MemcachedDicMap;

public class Ticket {
    private int id;
    private String startStation;
    private String stopStation;
    private String departureTime;
    private Double price;
    private Integer ticketNum;
    private String type;
    private String typeName;
    private Integer routeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        //从数据字典中获取类型
        if (typeName == null) {
            typeName = MemcachedDicMap.getFieldDetailByMemcachedAccess("ticket", "type", type);
        }

        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", startStation='" + startStation + '\'' +
                ", stopStation='" + stopStation + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", price=" + price +
                ", ticketNum=" + ticketNum +
                ", type=" + type +
                ", routeId=" + routeId +
                '}';
    }
}
