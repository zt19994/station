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
    private Integer state;
    private String orderNum;
    private String createTime;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderPage{" +
                "id=" + id +
                ", startStation='" + startStation + '\'' +
                ", stopStation='" + stopStation + '\'' +
                ", userName='" + userName + '\'' +
                ", num=" + num +
                ", state=" + state +
                ", orderNum='" + orderNum + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
