package com.station.service;

import com.station.entity.User;
import com.station.pageEntity.PageInfo;
import com.station.queryEntity.TicketQueryObj;

public interface ITicketOtherService {

    /**
     * 查询其他车站车票
     *
     * @param ticketQueryObj
     * @return
     */
    PageInfo queryOtherTicket(TicketQueryObj ticketQueryObj);

    /**
     * 购买其他车站的票
     *
     * @return
     */
    Boolean buyOtherTicket(User user, Integer ticketId);
}

