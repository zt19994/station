package com.station.controller.portal;

import com.station.entity.OrderPage;
import com.station.entity.Result;
import com.station.entity.User;
import com.station.pageEntity.PageInfo;
import com.station.queryEntity.OrderQueryObj;
import com.station.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
     *
     * @return
     */
    @RequestMapping("order")
    public ModelAndView toTicketOrder() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        return modelAndView;
    }

    /**
     * 返回订单数据
     *
     * @return
     */
    @RequestMapping("data")
    @ResponseBody
    public List<OrderPage> getData() {
        return orderService.queryAllOrder();
    }


    /**
     * 条件查询
     *
     * @param orderQueryObj
     * @return
     */
    @RequestMapping("data2")
    @ResponseBody
    public List<OrderPage> getData2(OrderQueryObj orderQueryObj) {
        return orderService.queryByInfo(orderQueryObj);
    }

    /**
     * 条件查询加分页
     *
     * @param orderQueryObj
     * @return
     */
    @RequestMapping("data3")
    @ResponseBody
    public PageInfo<OrderPage> getPageInfo(OrderQueryObj orderQueryObj, HttpSession session) {
        User user = (User) session.getAttribute("LOGIN_IN_SESSION");
        String userName = user.getUserName();
        orderQueryObj.setUserName(userName);
        System.out.println(userName);
        return orderService.getPageInfo(orderQueryObj);
    }


    /**
     * 退票
     *
     * @param id
     * @return
     */
    @RequestMapping("refundTicket")
    @ResponseBody
    public Result refundTicket(Integer id) {
        Result<Object> result = new Result<>();
        //调用方法，判断是否修改状态成功
        Boolean aBoolean = orderService.refundTicket(id);
        if (aBoolean) {
            //退票成功
            result.setCode("0000");
            result.setMsg("退票成功");
        } else {
            result.setCode("0001");
            result.setMsg("退票失败");
        }
        return result;
    }

}
