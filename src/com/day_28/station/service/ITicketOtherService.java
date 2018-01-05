package com.day_28.station.service;

import com.day_28.station.entity.User;
import com.day_28.station.pageEntity.PageInfo;
import com.day_28.station.queryEntity.TicketQueryObj;

public interface ITicketOtherService {

    /**
     * 查询其他车站车票
     * @param ticketQueryObj
     * @return
     */
    PageInfo queryOtherTicket(TicketQueryObj ticketQueryObj);

    /**
     * 购买其他车站的票
     * @return
     */
    Boolean buyOtherTicket(User user, Integer ticketId);
}

