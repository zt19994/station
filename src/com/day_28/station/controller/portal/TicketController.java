package com.day_28.station.controller.portal;


import com.day_28.station.entity.Ticket;
import com.day_28.station.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private ITicketService iTicketService;

    @RequestMapping("/list")
    public ModelAndView listTicket(){
        ModelAndView modelAndView = new ModelAndView();

        List<Ticket> tickets = iTicketService.queryAllTicket();

        modelAndView.addObject("tickets", tickets);
        //设置视图地址
        modelAndView.setViewName("list");

        return modelAndView;
    }
}
