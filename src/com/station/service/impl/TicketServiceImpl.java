package com.station.service.impl;

import com.station.dao.ITicketDao;
import com.station.dao.ITicketOrderDao;
import com.station.entity.Ticket;
import com.station.entity.TicketOrder;
import com.station.pageEntity.PageInfo;
import com.station.queryEntity.TicketQueryObj;
import com.station.service.ITicketService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements ITicketService {
    @Autowired
    private ITicketDao iTicketDao;

    @Autowired
    private ITicketOrderDao iTicketOrderDao;


    @Override
    public void addTicket(Ticket ticket) {

    }

    @Override
    public void deleteTicket(int id) {

    }

    @Override
    public void updateTicket(Ticket ticket) {

    }

    @Override
    public List<Ticket> queryAllTicket() {
        List<Ticket> tickets = iTicketDao.queryAllTicket();
        return tickets;
    }

    @Override
    public int count(TicketQueryObj ticketQueryObj) {
        int count = iTicketDao.count(ticketQueryObj);
        return count;
    }

    @Override
    public List<Ticket> queryByInfo(TicketQueryObj ticketQueryObj) {
        List<Ticket> tickets = iTicketDao.queryByInfo(ticketQueryObj);
        return tickets;
    }

    @Override
    public PageInfo<Ticket> getPageInfo(TicketQueryObj ticketQueryObj) {
        PageInfo<Ticket> pageInfo = new PageInfo<>();
        //封装页面对象 1.车票列表
        List<Ticket> tickets1 = new ArrayList<>();
        List<Ticket> tickets = iTicketDao.queryByInfo(ticketQueryObj);
        for (Ticket ticket : tickets) {
            String departureTime = ticket.getDepartureTime();
            String substring = departureTime.substring(0, 19);
            ticket.setDepartureTime(substring);
            tickets1.add(ticket);
        }
        pageInfo.setList(tickets1);
        //车票总条数
        int count = iTicketDao.count(ticketQueryObj);
        pageInfo.setCount(count);
        //每页条数
        Integer pageSize = ticketQueryObj.getPageSize();
        pageInfo.setPageSize(pageSize);
        //当前页
        Integer currentPage = ticketQueryObj.getCurrentPage();
        pageInfo.setCurrentPage(currentPage);
        //总页数，通过计算得到
        int totalPage = ((count - 1) / pageSize) + 1;
        pageInfo.setTotalPage(totalPage);
        return pageInfo;
    }

    @Override
    public Boolean buyTicket(Integer userId, Integer ticketId, String token) {
        //1.查询原来的余票
        Ticket ticket = iTicketDao.queryById(ticketId);
        Integer ticketNum = ticket.getTicketNum();
        //2.修改余票数量
        //a.判断余票是否小于0，小于则返回false
        if (ticketNum <= 0) {
            return false;
        }
        if (StringUtils.isNotBlank(token)) {
            //外网
            //b.余票减少一张
            ticketNum = ticketNum - 1;
            //更新车票信息
            ticket.setTicketNum(ticketNum);
            iTicketDao.updateTicket(ticket);
            //3.生成订单, ticketId, userId, ticketNum购买数量, orderNum订单编号
            TicketOrder ticketOrder = new TicketOrder();
            ticketOrder.setTicketId(ticketId);
            //id为5，是外网
            ticketOrder.setUserId(5);
            //购买1张
            ticketOrder.setNum(1);
            //通过UUID生成订单编号
            ticketOrder.setState(1);
            String orderNum = UUID.randomUUID().toString();
            ticketOrder.setOrderNum(orderNum);
            //调用方法生成订单
            iTicketOrderDao.addTicketOrder(ticketOrder);
            return true;
        }
        //b.余票减少一张
        ticketNum = ticketNum - 1;
        //更新车票信息
        ticket.setTicketNum(ticketNum);
        iTicketDao.updateTicket(ticket);
        //3.生成订单, ticketId, userId, ticketNum购买数量, orderNum订单编号
        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setTicketId(ticketId);
        ticketOrder.setUserId(userId);
        //购买1张
        ticketOrder.setNum(1);
        //通过UUID生成订单编号
        ticketOrder.setState(1);
        String orderNum = UUID.randomUUID().toString();
        ticketOrder.setOrderNum(orderNum);
        //调用方法生成订单
        iTicketOrderDao.addTicketOrder(ticketOrder);

        return true;
    }
}
