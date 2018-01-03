package com.day_28.station.dao;

import com.day_28.station.entity.TicketOrder;

import java.util.List;

public interface ITicketOrderDao {
    /**
     * 增加车票订单
     * @param ticketOrder
     */
    void addTicketOrder(TicketOrder ticketOrder);

    /**
     * 查询所有
     * @return
     */
    List<TicketOrder> queryAll();
}
