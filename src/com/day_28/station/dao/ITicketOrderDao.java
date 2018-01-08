package com.day_28.station.dao;

import com.day_28.station.entity.OrderPage;
import com.day_28.station.entity.TicketOrder;
import com.day_28.station.queryEntity.OrderQueryObj;

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

    /**
     * 查询所有订单模型信息
     * @return
     */
    List<OrderPage> queryAllOrderPage();

    /**
     * 通过订单查询对象查询订单信息
     * @param orderQueryObj
     * @return
     */
    List<OrderPage> queryByInfo(OrderQueryObj orderQueryObj);

    /**
     * 条件查询总条数
     * @param orderQueryObj
     * @return
     */
    int count(OrderQueryObj orderQueryObj);

    /**
     * 通过订单id查询订单
     * @param id
     * @return
     */
    TicketOrder queryOrderById(Integer id);

    /**
     * 退票处理，把state修改为2
     * @param id
     */
    void refundTicket(Integer id);
}
