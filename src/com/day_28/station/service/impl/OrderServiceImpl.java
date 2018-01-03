package com.day_28.station.service.impl;

import com.day_28.station.dao.ITicketOrderDao;
import com.day_28.station.entity.OrderPage;
import com.day_28.station.pageEntity.PageInfo;
import com.day_28.station.queryEntity.OrderQueryObj;
import com.day_28.station.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private ITicketOrderDao ticketOrderDao;

    @Override
    public List<OrderPage> queryAllOrder() {
        List<OrderPage> orderPages = ticketOrderDao.queryAllOrderPage();
        return orderPages;
    }

    @Override
    public List<OrderPage> queryByInfo(OrderQueryObj orderQueryObj) {
        List<OrderPage> orderPages = ticketOrderDao.queryByInfo(orderQueryObj);
        return orderPages;
    }


    @Override
    public PageInfo<OrderPage> getPageInfo(OrderQueryObj orderQueryObj) {
        PageInfo<OrderPage> pageInfo = new PageInfo<>();
        //封装页面信息
        //获取订单列表
        List<OrderPage> orderPages = ticketOrderDao.queryByInfo(orderQueryObj);
        pageInfo.setList(orderPages);
        //当前页
        pageInfo.setCurrentPage(orderQueryObj.getCurrentPage());
        //总页数
        int count = ticketOrderDao.count(orderQueryObj);
        pageInfo.setCount(count);
        //每页条数
        pageInfo.setPageSize(orderQueryObj.getPageSize());
        //
        int totalPage = (count - 1)/orderQueryObj.getPageSize() + 1;
        pageInfo.setTotalPage(totalPage);
        return pageInfo;
    }
}
