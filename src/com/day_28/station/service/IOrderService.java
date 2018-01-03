package com.day_28.station.service;

import com.day_28.station.entity.OrderPage;
import com.day_28.station.pageEntity.PageInfo;
import com.day_28.station.queryEntity.OrderQueryObj;

import java.util.List;

public interface IOrderService {

    /**
     * 查询所有订单信息
     * @return
     */
    List<OrderPage> queryAllOrder();

    /**
     * 通过订单查询对象查询订单信息
     * @param orderQueryObj
     * @return
     */
    List<OrderPage> queryByInfo(OrderQueryObj orderQueryObj);

    /**
     * 条件查询加分页
     * @param orderQueryObj
     * @return
     */
    PageInfo<OrderPage> getPageInfo(OrderQueryObj orderQueryObj);
}
