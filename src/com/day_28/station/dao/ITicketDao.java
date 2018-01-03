package com.day_28.station.dao;

import com.day_28.station.queryEntity.TicketQueryObj;
import com.day_28.station.entity.Ticket;

import java.util.List;

public interface ITicketDao {
    /**
     * 添加车票
     * @param ticket
     */
    void addTicket(Ticket ticket);

    /**
     * 删除车票
     * @param id
     */
    void deleteTicket(int id);

    /**
     * 更新车票
     * @param ticket
     */
    void updateTicket(Ticket ticket);

    /**
     * 查询所有车票列表
     * @return
     */
    List<Ticket> queryAllTicket();

    /**
     * 查询总车票数量
     * @return
     */
    int count(TicketQueryObj ticketQueryObj);

    /**
     * 通过条件查询
     * @return
     */
    List<Ticket> queryByInfo(TicketQueryObj ticketQueryObj);

    /**
     * 通过车票id查询车票信息
     * @param id
     * @return
     */
    Ticket queryById(Integer id);
}
