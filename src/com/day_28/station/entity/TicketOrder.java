package com.day_28.station.entity;

public class TicketOrder {
    private Integer id;
    private Integer ticketId;
    private Integer userId;
    private Integer num;
    private String orderNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return "TicketOrder{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", userId=" + userId +
                ", num=" + num +
                ", orderNum=" + orderNum +
                '}';
    }
}
