package com.day_28.station.commons;

import com.day_28.station.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        User loginInSession = (User) session.getAttribute("LOGIN_IN_SESSION");
        if (loginInSession == null) {
            System.out.println("没有登录");
            //跳转到登录页面
            response.sendRedirect("/login/toLogin");
        }else {
            System.out.println("已经登录");
        }

        return true;
    }
}
