package com.station.commons;

import com.station.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String token1 = request.getParameter("token");
        String host = request.getHeader("Host");
        String token = "123456";
        User loginInSession = (User) session.getAttribute("LOGIN_IN_SESSION");
        if (loginInSession == null) {

            if (token != null && token1.equals(token)) {
                System.out.println("外网登录");
                System.out.println(host);
                return true;
            }
            //跳转到登录页面
            System.out.println("没有登录");
            response.sendRedirect("/login/toLogin");
        } else {
            System.out.println("已经登录");

        }

        return true;
    }
}
