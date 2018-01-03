package com.day_28.station.entity;

public class Ticket {
    private int id;
    private String startStation;
    private String stopStation;
    private String departureTime;
    private Double price;
    private Integer ticketNum;
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
                ", routeId=" + routeId +
                '}';
    }
}
