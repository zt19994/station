package com.station.controller.portal;


import com.station.entity.Result;
import com.station.entity.Ticket;
import com.station.entity.User;
import com.station.pageEntity.PageInfo;
import com.station.queryEntity.TicketQueryObj;
import com.station.service.ITicketService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/ticket2/")
public class TicketController {
    @Autowired
    private ITicketService ticketService;

    /**
     * 请求页面
     *
     * @return
     */
    @RequestMapping("page")
    public ModelAndView listTicket() {
        ModelAndView modelAndView = new ModelAndView();

        //设置视图地址
        modelAndView.setViewName("ticketList");

        return modelAndView;
    }

    /**
     * 请求数据
     *
     * @return
     */
    @RequestMapping("data")
    @ResponseBody
    public List<Ticket> data() {
        return ticketService.queryAllTicket();
    }

    /**
     * 查询后的数据
     *
     * @param ticketQueryObj
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public List<Ticket> query(TicketQueryObj ticketQueryObj) {
        return ticketService.queryByInfo(ticketQueryObj);
    }

    /**
     * 查询加分页后的数据
     *
     * @param ticketQueryObj
     * @return
     */
    @RequestMapping("query2")
    @ResponseBody
    public Result query2(TicketQueryObj ticketQueryObj) {
        PageInfo<Ticket> pageInfo = ticketService.getPageInfo(ticketQueryObj);
        Result<Object> result = new Result<>();
        result.setData(pageInfo);
        result.setMsg("获取数据");
        result.setCode("0000");
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("buyTicket")
    @ResponseBody
    public Result buyTicket(Integer id, String token, HttpSession httpSession) {
        Result<Object> result = new Result<>();

        //在session中获取用户id
        User loginInSession = (User) httpSession.getAttribute("LOGIN_IN_SESSION");
        Boolean aBoolean = null;
        if (StringUtils.isNotBlank(token)) {
            aBoolean = ticketService.buyTicket(5, id, token);
        } else {
            Integer userId = loginInSession.getId();
            //调用业务方法，判断是否购票成功
            aBoolean = ticketService.buyTicket(userId, id, token);
        }
        if (aBoolean) {
            //为true，购票成功
            result.setCode("0000");
            result.setMsg("购票成功");
        } else {
            result.setCode("0001");
            result.setMsg("购票失败");
        }
        return result;
    }

}
