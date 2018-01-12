package com.station.controller.portal;

import com.station.entity.Result;
import com.station.entity.User;
import com.station.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register/")
public class UserRegisterController {
    @Autowired
    private IUserService userService;

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @RequestMapping("toRegister")
    public ModelAndView toAddUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }


    /**
     * 检查注册
     *
     * @return
     */
    @RequestMapping("checkRegister")
    @ResponseBody
    public Result logon(User user) {
        Result result = new Result();
        //先检查用户输入是否为空
        if (user.getUserName() != null && !user.getUserName().trim().equals("") &&
                user.getPassword() != null && !user.getPassword().trim().equals("")) {
            //然后检出注册是否重复
            Boolean aBoolean = userService.checkRegister(user);
            //aBoolean位true，则注册成功
            if (aBoolean) {
                result.setCode("0000");
                result.setMsg("注册成功");
                result.setSuccess(true);
            } else {
                result.setCode("0001");
                result.setMsg("用户已经存在");
                result.setSuccess(false);
            }
        } else {
            result.setCode("0002");
            result.setMsg("输入错误，请重新注册");
            result.setSuccess(false);
        }
        return result;
    }

}
