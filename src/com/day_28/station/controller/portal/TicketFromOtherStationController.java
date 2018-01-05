package com.day_28.station.controller.portal;

import com.day_28.station.entity.Result;
import com.day_28.station.entity.Ticket;
import com.day_28.station.entity.User;
import com.day_28.station.pageEntity.PageInfo;
import com.day_28.station.queryEntity.TicketQueryObj;
import com.day_28.station.service.ITicketOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/otherStation/")
public class TicketFromOtherStationController {
    @Autowired
    private ITicketOtherService ticketOtherService;

    @RequestMapping("toOtherStationList")
    public ModelAndView toOtherStationList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("otherStationList");
        return modelAndView;
    }

    @RequestMapping("loadData")
    @ResponseBody
    public Result loadData(TicketQueryObj ticketQueryObj){
        Result<Object> result = new Result<>();
        PageInfo pageInfo = ticketOtherService.queryOtherTicket(ticketQueryObj);
        result.setData(pageInfo);
        return result;
    }

    @RequestMapping("buyOtherTicket")
    @ResponseBody
    public Result buyOtherTicket(Integer id){
        Result<Object> result = new Result<>();
        User user = new User();
        user.setId(9);
        Boolean aBoolean = ticketOtherService.buyOtherTicket(user, id);
        if (aBoolean){
            //为true，购票成功
            result.setCode("0000");
            result.setMsg("购票成功");
        }else {
            result.setCode("0001");
            result.setMsg("购票失败");
        }
        return result;
    }
}
