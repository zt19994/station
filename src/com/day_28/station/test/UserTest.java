package com.day_28.station.test;

import com.day_28.station.dao.IUserDao;
import com.day_28.station.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class UserTest {
    @Autowired
    private IUserDao iUserDao;

    /**
    * 测试添加用户
    */
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUserName("用户5");
        user.setPassword("555");
        user.setPhone(55555);
        user.setIdentityCard(1663546);
        user.setState(0);
        iUserDao.addUser(user);
    }

    /**
    * 测试查询所有用户
    */
    @Test
    public void testQueryAllUser(){
        List<User> users = iUserDao.queryAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
    * 测试通过名字查询用户信息
    */
    @Test
    public void testQueryByName(){
        User user = new User();
        user.setUserName("用户2");
        User user1 = iUserDao.queryByName(user);
        System.out.println(user1);
    }
}
