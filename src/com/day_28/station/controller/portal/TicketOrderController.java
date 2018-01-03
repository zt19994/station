package com.day_28.station.controller.portal;

import com.day_28.station.entity.OrderPage;
import com.day_28.station.entity.Result;
import com.day_28.station.pageEntity.PageInfo;
import com.day_28.station.queryEntity.OrderQueryObj;
import com.day_28.station.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 车票订单控制层
 */
@Controller
@RequestMapping("/order/")
public class TicketOrderController {
    @Autowired
    private IOrderService orderService;

    /**
     * 跳转到订单列表页面
     * @return
     */
    @RequestMapping("order")
    public ModelAndView toTicketOrder(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        return modelAndView;
    }

    /**
     * 返回订单数据
     * @return
     */
    @RequestMapping("data")
    @ResponseBody
    public List<OrderPage> getData(){
        List<OrderPage> orderPages = orderService.queryAllOrder();
        return orderPages;
    }


    /**
     * 条件查询
     * @param orderQueryObj
     * @return
     */
    @RequestMapping("data2")
    @ResponseBody
    public List<OrderPage> getData2(OrderQueryObj orderQueryObj){
        List<OrderPage> orderPages = orderService.queryByInfo(orderQueryObj);
        return orderPages;
    }

    /**
     * 条件查询加分页
     * @param orderQueryObj
     * @return
     */
    @RequestMapping("data3")
    @ResponseBody
    public PageInfo<OrderPage> getPageInfo(OrderQueryObj orderQueryObj){
        PageInfo<OrderPage> pageInfo = orderService.getPageInfo(orderQueryObj);
        return pageInfo;
    }

}
