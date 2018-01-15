package com.station.controller.backend;

import com.station.entity.Result;
import com.station.entity.User;
import com.station.pageEntity.PageInfo;
import com.station.queryEntity.UserQueryObj;
import com.station.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户管理系统
 */
@Controller
@RequestMapping("/manage/login/")
public class ManageUserController {
    @Autowired
    private IUserService userService;

    /**
     * 请求用户列表页面
     * @return
     */
    @RequestMapping("userList")
    public ModelAndView userList(HttpSession session){
        //1.校验用户是否登录
        User user = (User)session.getAttribute("LOGIN_IN_SESSION");
        ModelAndView modelAndView = new ModelAndView();
        if (user==null){
            //未登录，跳转到登录页面
            //使用重定向来返回登录页面
            modelAndView.setViewName("redirect:/login/toLogin");
            return modelAndView;
        }
        modelAndView.setViewName("userList");
        return modelAndView;
    }

    /**
     * 请求管理用户数据
     * @return
     */
    @RequestMapping("userData")
    @ResponseBody
    public List<User> userDate(){
        List<User> users = userService.queryAllUser();
        return users;
    }

    /**
     * 通过条件查询用户信息
     * @param userQueryObj
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public List<User> query(UserQueryObj userQueryObj){
        List<User> users = userService.queryByInfo(userQueryObj);
        return users;
    }

    /**
     * 条件查询，并且加上分页
     * @param userQueryObj
     * @return
     */
    @RequestMapping("query2")
    @ResponseBody
    public PageInfo<User> query2(UserQueryObj userQueryObj){
        PageInfo<User> pageInfo = userService.getPageInfo(userQueryObj);
        return pageInfo;
    }


    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("deleteUser")
    @ResponseBody
    public List<User> deleteUser(Integer id){
        //删除用户
        userService.deleteUser(id);
        //再查询用户
        List<User> users = userService.queryAllUser();
        return users;
    }

    /**
     * 跳转到更新用户信息页面
     * @param id
     * @return
     */
    @RequestMapping("toUpdateUser")
    public ModelAndView toUpdateUser(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        modelAndView.setViewName("updateUser");
        return modelAndView;
    }

    /**
     * 通过用户id查询用户信息
     * @param id
     * @return
     */
    @RequestMapping("getUserDataById")
    @ResponseBody
    public Result<User> getUpdateUserData(int id){
        Result<User> result = new Result<User>();
        //通过id查询用户信息
        User user = userService.queryById(id);

        result.setData(user);
        return result;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping("updateData")
    @ResponseBody
    public Result updateData(User user){
        Result<Object> result = new Result<>();
        //调用业务方法，更新
        if (user.getId()==null){
            userService.addUser(user);
        }else {
            userService.updateUser(user);
        }
        return result;
    }

}
