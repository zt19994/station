package com.day_28.station.dao;

import com.day_28.station.entity.User;
import com.day_28.station.queryEntity.UserQueryObj;

import java.util.List;

public interface IUserDao {
    /**
     * 增加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryAllUser();

    /**
     * 通过用户名查询
     * @param user
     * @return
     */
    User queryByName(User user);

    /**
     * 通过id查询用户信息
     * @return
     */
    User queryById(int id);

    /**
     * 通过条件查询
     * @param userQueryObj
     * @return
     */
    List<User> queryByInfo(UserQueryObj userQueryObj);
}
