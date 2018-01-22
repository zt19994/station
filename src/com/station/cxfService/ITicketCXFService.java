package com.station.cxfService;

import com.station.entity.Ticket;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface ITicketCXFService {

    /**
     * 查询所有车票列表
     *
     * @return
     */
    List<Ticket> queryAllTicket();

    /**
     * 锁票
     *
     * @param jsonStr json字符串，含有userId,ticketId,ticketNum
     * @return
     */
    String lockTicket(String jsonStr);

    /**
     * 购票，修改订单状态
     *
     * @param jsonStr 含有orderNum
     * @return
     */
    String buyTicket(String jsonStr);
}
