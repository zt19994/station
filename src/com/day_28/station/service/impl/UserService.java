package com.day_28.station.service.impl;

import com.day_28.station.dao.IUserDao;
import com.day_28.station.entity.User;
import com.day_28.station.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserDao iUserDao;


    @Override
    public void addUser(User user) {
        user.setState(0);
        iUserDao.addUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        iUserDao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        iUserDao.updateUser(user);
    }

    @Override
    public User queryById(int id) {
        User user = iUserDao.queryById(id);
        return user;
    }

    @Override
    public User queryByName(User user) {
        User user1 = iUserDao.queryByName(user);
        return user1;
    }

    @Override
    public List<User> queryAllUser() {
        List<User> users = iUserDao.queryAllUser();
        return users;
    }

    @Override
    public Boolean checkLogin(User user) {
        //取出用户登录输入的信息
        String userName = user.getUserName();
        String password = user.getPassword();
        if (userName != null && !userName.trim().equals("")) {
            User user1 = iUserDao.queryByName(user);
            //获取查询出的用户密码
            String password1 = user1.getPassword();
            if (password.equals(password1)) {
                //用户密码相等，验证成功
                return true;
            }
        }
        return false;
    }


    @Override
    public Boolean checkRegister(User user) {
        //通过用户名查询，是否存在用户，存在则已经注册过了
        User user1 = iUserDao.queryByName(user);
        if (user1!=null){
            return false;
        }
        //不存在，则注册

        iUserDao.addUser(user);
        return true;
    }
}
