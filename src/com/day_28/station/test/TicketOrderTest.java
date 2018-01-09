package com.day_28.station.test;

import com.day_28.station.dao.ITicketOrderDao;
import com.day_28.station.entity.OrderPage;
import com.day_28.station.entity.TicketOrder;
import com.day_28.station.queryEntity.OrderQueryObj;
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
    private ITicketOrderDao ticketOrderDao;

    /**
    * 测试增加车票订单
    */
    @Test
    public void testAddTicketOrder(){
        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setTicketId(1);
        ticketOrder.setUserId(1);
        ticketOrder.setNum(1);
        ticketOrder.setState(1);
        String orderNum = UUID.randomUUID().toString();
        ticketOrder.setOrderNum(orderNum);
        System.out.println(ticketOrder);
        ticketOrderDao.addTicketOrder(ticketOrder);
    }

    /**
    * 测试查询所有车票订单
    */
    @Test
    public void testQueryAll(){
        List<TicketOrder> ticketOrders = ticketOrderDao.queryAll();
        for (TicketOrder ticketOrder : ticketOrders) {
            System.out.println(ticketOrder);
        }
    }

    /**
    * 测试查询订单模型信息
    */
    @Test
    public void testQueryAllOrderPage(){
        List<OrderPage> orderPages = ticketOrderDao.queryAllOrderPage();
        for (OrderPage orderPage : orderPages) {
            System.out.println(orderPage);
        }
    }

    /**
    * 测试条件查询
    */
    @Test
    public void testQueryByInfo(){
        OrderQueryObj orderQueryObj = new OrderQueryObj();
        orderQueryObj.setStartStation("成都");
        orderQueryObj.setMinTime("2017-12-26 00:00:00");
        List<OrderPage> orderPages = ticketOrderDao.queryByInfo(orderQueryObj);
        for (OrderPage orderPage : orderPages) {
            System.out.println(orderPage);
        }
    }

    /**
    * 测试条件查询总条数
    */
    @Test
    public void testCount(){
        OrderQueryObj orderQueryObj = new OrderQueryObj();
        orderQueryObj.setStartStation("南京");
        orderQueryObj.setMinTime("2017-12-26");
        int count = ticketOrderDao.count(orderQueryObj);
        System.out.println(count);
    }

    /**
    * 测试通过id查询订单信息
    */
    @Test
    public void testQueryById(){
        TicketOrder ticketOrder = ticketOrderDao.queryOrderById(2);
        System.out.println(ticketOrder);
    }
}
