package com.day_28.station.service.impl;

import com.day_28.station.dao.ITicketOrderDao;
import com.day_28.station.entity.Ticket;
import com.day_28.station.entity.TicketOrder;
import com.day_28.station.entity.User;
import com.day_28.station.pageEntity.PageInfo;
import com.day_28.station.queryEntity.TicketQueryObj;
import com.day_28.station.service.ITicketOtherService;
import com.day_28.station.util.HttpClientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketOtherServiceImpl implements ITicketOtherService {
    @Autowired
    private ITicketOrderDao ticketOrderDao;

    @Override
    public PageInfo<Ticket> queryOtherTicket(TicketQueryObj ticketQueryObj) {
        Integer currentPage1 = ticketQueryObj.getCurrentPage();
        Integer pageSize1 = ticketQueryObj.getPageSize();
        String url = "http://10.3.2.154:8080/ticket/data2?_currentPage=" + currentPage1 + "&_pageSize=" + pageSize1;
        PageInfo<Ticket> pageInfo = new PageInfo<>();
        List<Ticket> list1 = new ArrayList<>();

        try {
            String post = HttpClientUtil.post(url);
            JSONObject jsonObject = JSONObject.fromObject(post);
            int currentPage = jsonObject.getInt("currentPage");
            int totalPage = jsonObject.getInt("totalPage");
            int pageSize = jsonObject.getInt("pageSize");
            int count = jsonObject.getInt("count");
            JSONArray list = jsonObject.getJSONArray("list");
            for (int i=0;i<list.size();i++) {
                JSONObject jsonObject1 = list.getJSONObject(i);
                int id = jsonObject1.getInt("id");
                String startStation = jsonObject1.getString("startStation");
                String stopStation = jsonObject1.getString("stopStation");
                String departureTime = jsonObject1.getString("departureTime");
                String typeName = jsonObject1.getString("typeName");
                double price = jsonObject1.getDouble("price");
                int ticketNum = jsonObject1.getInt("surplusVote");

                Ticket ticket = new Ticket();
                ticket.setId(id);
                ticket.setStartStation(startStation);
                ticket.setStopStation(stopStation);
                ticket.setDepartureTime(departureTime);
                ticket.setTypeName(typeName);
                ticket.setPrice(price);
                ticket.setTicketNum(ticketNum);
                list1.add(ticket);
            }
            pageInfo.setTotalPage(totalPage);
            pageInfo.setPageSize(pageSize);
            pageInfo.setCount(count);
            pageInfo.setCurrentPage(currentPage);
            pageInfo.setList(list1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pageInfo;
    }

    @Override
    public Boolean buyOtherTicket(User user, Integer ticketId) {
        String url = "http://10.3.2.154:8080/ticket/data3?id=" + ticketId +"&token=123";
        try {
            String post = HttpClientUtil.post(url);
            JSONObject jsonObject = JSONObject.fromObject(post);
            boolean success = jsonObject.getBoolean("success");
            System.out.println(success);
            if (!success){
                return  false;
            }else {
                //保存订单
                TicketOrder ticketOrder = new TicketOrder();
                ticketOrder.setTicketId(ticketId);
                ticketOrder.setUserId(user.getId());
                //购买1张
                ticketOrder.setNum(1);
                //通过UUID生成订单编号
                String orderNum = UUID.randomUUID().toString();
                ticketOrder.setOrderNum(orderNum);
                //调用方法生成订单
                ticketOrderDao.addTicketOrder(ticketOrder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
