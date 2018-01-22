package com.station.service;

import com.station.entity.User;
import com.station.pageEntity.PageInfo;
import com.station.queryEntity.UserQueryObj;

import java.util.List;

public interface IUserService {
    /**
     * 增加用户
     *
     * @param user
     */
    boolean addUser(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 通过id查询用户信息
     *
     * @return
     */
    User queryById(int id);

    /**
     * 通过name查询用户信息
     *
     * @return
     */
    User queryByName(User user);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> queryAllUser();


    /**
     * 通过用户名查询
     *
     * @param user
     * @return
     */
    Boolean checkLogin(User user);


    /**
     * 检查登录
     *
     * @param user
     * @return
     */
    Boolean checkRegister(User user);

    /**
     * 通过条件查询用户信息
     *
     * @param userQueryObj
     * @return
     */
    List<User> queryByInfo(UserQueryObj userQueryObj);

    /**
     * 条件查询加分页，返回用户分页对象
     *
     * @param userQueryObj
     * @return
     */
    PageInfo<User> getPageInfo(UserQueryObj userQueryObj);
}
