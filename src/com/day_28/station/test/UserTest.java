package com.day_28.station.test;

import com.day_28.station.dao.IUserDao;
import com.day_28.station.entity.User;
import com.day_28.station.queryEntity.UserQueryObj;
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
    private IUserDao userDao;

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
        userDao.addUser(user);
    }

    /**
    * 测试查询所有用户
    */
    @Test
    public void testQueryAllUser(){
        List<User> users = userDao.queryAllUser();
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
        User user1 = userDao.queryByName(user);
        System.out.println(user1);
    }

    /**
    * 测试条件查询
    */
    @Test
    public void testQueryByInfo(){
        UserQueryObj userQueryObj = new UserQueryObj();
        userQueryObj.setUserName("用户");
        List<User> users = userDao.queryByInfo(userQueryObj);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
    * 测试条件查询总条数
    */
    @Test
    public void testCount(){
        UserQueryObj userQueryObj = new UserQueryObj();
        userQueryObj.setUserName("用户");
        int count = userDao.count(userQueryObj);
        System.out.println(count);
    }
}
