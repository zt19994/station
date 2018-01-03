package com.day_28.station.entity;

/**
 * 用于前端显示的订单模型
 */
public class OrderPage {
    private Integer id;
    private String startStation;
    private String stopStation;
    private String userName;
    private Integer num;
    private String orderNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "OrderPage{" +
                "id=" + id +
                ", startStation='" + startStation + '\'' +
                ", stopStation='" + stopStation + '\'' +
                ", userName='" + userName + '\'' +
                ", num=" + num +
                ", orderNum=" + orderNum +
                '}';
    }
}
