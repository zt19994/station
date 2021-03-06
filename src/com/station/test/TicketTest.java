package com.station.test;

import com.station.dao.ITicketDao;
import com.station.entity.Ticket;
import com.station.queryEntity.TicketQueryObj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TicketTest {
    @Autowired
    private ITicketDao iTicketDao;
    
    
    /**
    * 测试添加车票
    */
    @Test
    public void testAddTicket(){
        Ticket ticket = new Ticket();
        ticket.setStartStation("成都");
        ticket.setStopStation("北京");
        ticket.setDepartureTime("2018-1-16 10:00:00");
        ticket.setPrice(258.0);
        ticket.setTicketNum(40);
        ticket.setRouteId(3);
        ticket.setType("1");
        int i = iTicketDao.addTicket(ticket);
        System.out.println(i);
    }

    /**
     * 测试查询所有车票列表
     */
    @Test
    public void testQueryAllTicket() {
        List<Ticket> tickets = iTicketDao.queryAllTicket();
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    /**
    * 测试查询所有车票数
    */
    @Test
    public void testCount(){
        TicketQueryObj ticketQueryObj = new TicketQueryObj();
        //ticketQueryObj.setStartStation("成都");
        //ticketQueryObj.setStopStation("南充");
        ticketQueryObj.setMaxTime("2017-12-26");
        int count = iTicketDao.count(ticketQueryObj);
        System.out.println(count);
    }


    /**
     * 测试通过条件查询车票列表
     */
    @Test
    public void testQueryByInfo() {
        TicketQueryObj ticketQueryObj = new TicketQueryObj();
        //ticketQueryObj.setStartStation("成都");
        //ticketQueryObj.setStopStation("南充");
        ticketQueryObj.setMaxTime("2017-12-25");
        List<Ticket> tickets = iTicketDao.queryByInfo(ticketQueryObj);
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    /**
    * 测试通过id查询车票
    */
    @Test
    public void testQueryById(){
        Ticket ticket = iTicketDao.queryById(8);
        System.out.println(ticket);
    }

    /**
    * 测试车票更新
    */
    @Test
    public void testUpdateTicket(){
        Ticket ticket = new Ticket();
        ticket.setId(12);
        ticket.setStartStation("成都");
        ticket.setStopStation("南充");
        ticket.setDepartureTime("2018-1-2 18:00");
        ticket.setPrice(58.0);
        ticket.setTicketNum(40);
        ticket.setRouteId(5);
        iTicketDao.updateTicket(ticket);
    }

}
