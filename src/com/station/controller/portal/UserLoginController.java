package com.station.controller.portal;


import com.station.entity.Result;
import com.station.entity.User;
import com.station.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * 登录控制层
 */
@Controller
@RequestMapping("/login/")
public class UserLoginController {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    private IUserService userService;

    /**
     * 跳转到登录页面，并保存到httpSession中
     *
     * @return
     */
    @RequestMapping("toLogin")
    public ModelAndView toLogin(HttpSession httpSession) {
        logger.info("---log---");
        ModelAndView modelAndView = new ModelAndView();
        //获取登录随机数,为了防止表单提交重复
        String loginToken = UUID.randomUUID().toString();

        //后端token
        httpSession.setAttribute("LOGIN_TOKEN_IN_SESSION", loginToken);
        //保存在前端的token
        modelAndView.addObject("loginToken", loginToken);
        modelAndView.setViewName("login");
        logger.info("---afterlog---");
        return modelAndView;
    }


    /**
     * 注销
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("logout")
    public ModelAndView logout(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        httpSession.removeAttribute("LOGIN_IN_SESSION");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 检查登录，比较前后端token值，防止重复提交
     *
     * @param user
     * @param loginToken
     * @param httpSession
     * @return
     */
    @RequestMapping("checkLogin")
    @ResponseBody
    public Result checkLogin(User user, String loginToken, HttpSession httpSession) {
        Result<Object> result = new Result<>();
        //取出用户登录输入的信息
        String userName = user.getUserName();
        String password = user.getPassword();
        if (userName == null || userName.trim().equals("")) {
            //用户名为空
            System.out.println("用户名为空");
            result.setCode("0001");
            result.setMsg("用户名为空");
            return result;
        } else if (password == null || password.trim().equals("")) {
            System.out.println("密码为空");
            result.setCode("0001");
            result.setMsg("密码为空");
            return result;
        }
        //后端获取token信息
        String tokenInSession = (String) httpSession.getAttribute("LOGIN_TOKEN_IN_SESSION");
        if (tokenInSession == null) {
            //没有获取到token，重复提交
            System.out.println("重复提交1");
            result.setCode("0003");
            result.setMsg("重复提交1");
            return result;
        } else if (!tokenInSession.equals(loginToken)) {
            System.out.println("重复提交2");
            result.setCode("0003");
            result.setMsg("重复提交2");
            return result;
        }

        //第一次登录成功后，删除token
        System.out.println("第一次成功登录");
        httpSession.removeAttribute("LOGIN_TOKEN_IN_SESSION");

        //调用service方法检查的登录
        Boolean aBoolean = userService.checkLogin(user);
        if (aBoolean) {
            //登录成功,并且把用户信息存放session
            result.setCode("0000");
            //需要保存用户id
            User user1 = userService.queryByName(user);
            httpSession.setAttribute("LOGIN_IN_SESSION", user1);

        } else {
            //登录失败
            result.setCode("0001");
            result.setMsg("登录失败");
        }
        result.setSuccess(aBoolean);
        return result;
    }
}
