package com.station.service.impl;

import com.station.dao.ITicketDao;
import com.station.dao.ITicketOrderDao;
import com.station.entity.OrderPage;
import com.station.entity.Ticket;
import com.station.entity.TicketOrder;
import com.station.pageEntity.PageInfo;
import com.station.queryEntity.OrderQueryObj;
import com.station.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private ITicketOrderDao ticketOrderDao;

    @Autowired
    private ITicketDao ticketDao;

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
        List<OrderPage> orderPages1 = new ArrayList<>();
        for (OrderPage orderPage : orderPages) {
            String createTime = orderPage.getCreateTime();
            String substring = createTime.substring(0, 19);
            orderPage.setCreateTime(substring);
            orderPages1.add(orderPage);
        }
        pageInfo.setList(orderPages1);
        //当前页
        pageInfo.setCurrentPage(orderQueryObj.getCurrentPage());
        //总页数
        int count = ticketOrderDao.count(orderQueryObj);
        pageInfo.setCount(count);
        //每页条数
        pageInfo.setPageSize(orderQueryObj.getPageSize());
        //
        int totalPage = (count - 1) / orderQueryObj.getPageSize() + 1;
        pageInfo.setTotalPage(totalPage);
        return pageInfo;
    }

    @Override
    public Boolean refundTicket(Integer orderId) {
        //1.通过orderId查询到订单
        TicketOrder ticketOrder = ticketOrderDao.queryOrderById(orderId);
        //2.判断订单状态是否为1 ，1:已出售 2:退票
        Integer state = ticketOrder.getState();
        Integer ticketId = ticketOrder.getTicketId();
        if (state == 1) {
            //a.状态为已出售，修改状态为退票 2
            ticketOrderDao.refundTicket(orderId);
            //b.调用方法，修改车票数量，增加一张， 注意只有state为1，才能退票
            //先查询出购买的哪张车票
            Ticket ticket = ticketDao.queryById(ticketId);
            //获取当前车票数量
            Integer ticketNum = ticket.getTicketNum();
            ticketNum = ticketNum + 1;
            ticket.setTicketNum(ticketNum);
            //更新车票数量
            ticketDao.updateTicket(ticket);
            //c.若state为1，则修改state为2 返回true  否则返回false
            return true;
        }
        return false;
    }
}
