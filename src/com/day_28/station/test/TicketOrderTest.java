package com.day_28.station.test;

import com.day_28.station.dao.ITicketOrderDao;
import com.day_28.station.entity.TicketOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TicketOrderTest {
    @Autowired
    private ITicketOrderDao iTicketOrderDao;

    /**
    * 测试增加车票订单
    */
    @Test
    public void testAddTicketOrder(){
        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setTicketId(1);
        ticketOrder.setUserId(1);
        ticketOrder.setNum(1);
        String orderNum = UUID.randomUUID().toString();
        ticketOrder.setOrderNum(orderNum);
        System.out.println(ticketOrder);
        iTicketOrderDao.addTicketOrder(ticketOrder);
    }

    /**
    * 测试查询所有车票订单
    */
    @Test
    public void testQueryAll(){
        List<TicketOrder> ticketOrders = iTicketOrderDao.queryAll();
        for (TicketOrder ticketOrder : ticketOrders) {
            System.out.println(ticketOrder);
        }
    }
}
