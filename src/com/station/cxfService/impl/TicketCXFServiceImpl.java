package com.station.cxfService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.station.cxfService.ITicketCXFService;
import com.station.dao.ITicketDao;
import com.station.dao.ITicketOrderDao;
import com.station.entity.Ticket;
import com.station.entity.TicketOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;
import java.util.UUID;

@WebService
@Service
public class TicketCXFServiceImpl implements ITicketCXFService {
    @Autowired
    private ITicketDao ticketDao;

    @Autowired
    private ITicketOrderDao ticketOrderDao;


    @Override
    public List<Ticket> queryAllTicket() {
        List<Ticket> tickets = ticketDao.queryAllTicket();
        return tickets;
    }

    @Override
    public String lockTicket(String jsonStr) {
        /*String userId, String ticketId, String ticketNum*/
        //0.解析jsonStr字符串
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String userId = jsonObject.getString("userId");
        String ticketId = jsonObject.getString("ticketId");
        String ticketNum = jsonObject.getString("ticketNum");

        //1.查询原来的余票
        Ticket ticket = ticketDao.queryById(Integer.valueOf(ticketId));
        Integer num = ticket.getTicketNum();

        //2.修改余票数量
        //a.判断余票是否小于购票数，小于则返回false
        if (num <= Integer.valueOf(ticketNum)) {
            JSONObject error = new JSONObject();
            error.put("msg","余票不足");
            error.put("code","0001");
            error.put("success",false);
            return error.toJSONString();
        }
        //b.余票减少购票张数
        num = num - Integer.valueOf(ticketNum);
        //更新车票信息
        ticket.setTicketNum(num);
        ticketDao.updateTicket(ticket);
        //3.生成订单, ticketId, userId, ticketNum购买数量, orderNum订单编号
        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setTicketId(Integer.valueOf(ticketId));
        ticketOrder.setUserId(Integer.valueOf(userId));
        //购买张数
        ticketOrder.setNum(Integer.valueOf(ticketNum));
        //通过UUID生成订单编号  状态0为未支付
        ticketOrder.setState(0);
        String orderNum = UUID.randomUUID().toString();
        ticketOrder.setOrderNum(orderNum);
        //调用方法生成订单
        ticketOrderDao.addTicketOrder(ticketOrder);

        //返回orderNum
        JSONObject success = new JSONObject();
        success.put("data",orderNum);
        success.put("code","0000");
        success.put("msg","锁票成功");
        success.put("success",true);
        //转化为json格式
        return success.toJSONString();
    }


    @Override
    public String buyTicket(String jsonStr) {
        //0.解析jsonStr字符串
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String orderNum = jsonObject.getString("orderNum");

        //1.通过orderNum查询订单信息
        TicketOrder ticketOrder = ticketOrderDao.selectByOrderNum(orderNum);
        //2.修改订单状态 1.为已支付，购买成功
        ticketOrder.setState(1);
        int i = ticketOrderDao.updateOrder(ticketOrder);

        if (i==0){
            JSONObject error = new JSONObject();
            error.put("code","0003");
            error.put("msg","失败");
            error.put("success",false);
            return error.toJSONString();
        }
        JSONObject success = new JSONObject();
        success.put("code","0000");
        success.put("msg","购票成功");
        success.put("success",true);
        return success.toJSONString();
    }
}
