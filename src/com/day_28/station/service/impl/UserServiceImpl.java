package com.day_28.station.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.day_28.station.commons.map.MemcachedAccess;
import com.day_28.station.commons.map.MemcachedDicMap;
import com.day_28.station.dao.IResourceDao;
import com.day_28.station.dao.IUserDao;
import com.day_28.station.entity.Resource;
import com.day_28.station.entity.User;
import com.day_28.station.pageEntity.PageInfo;
import com.day_28.station.queryEntity.UserQueryObj;
import com.day_28.station.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private IResourceDao resourceDao;

    @Override
    public void addUser(User user) {
        user.setState(0);
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User queryById(int id) {
        User user = userDao.queryById(id);
        return user;
    }

    @Override
    public User queryByName(User user) {
        User user1 = userDao.queryByName(user);
        return user1;
    }

    @Override
    public List<User> queryAllUser() {
        List<User> users = userDao.queryAllUser();
        return users;
    }


    @Override
    public Boolean checkLogin(User user) {
        //取出用户登录输入的信息
        String userName = user.getUserName();
        String password = user.getPassword();
        if (userName != null && !userName.trim().equals("")) {
            User user1 = userDao.queryByName(user);
            //获取查询出的用户密码
            String password1 = user1.getPassword();
            if (password.equals(password1)) {
                //用户密码相等，验证成功

                //登录成功后，设置权限管理，把权限放入缓存 key value
                String key1 = "Resource_" + user1.getId();
                //通过id查询出权限

                List<Resource> value = resourceDao.getResourceList(user1.getId());
                String s = JSON.toJSONString(value);
                List<Resource> resources = JSONArray.parseArray(s, Resource.class);
                MemcachedDicMap.putResourceMap(key1, resources);

                return true;
            }
        }
        return false;
    }


    @Override
    public Boolean checkRegister(User user) {
        //通过用户名查询，是否存在用户，存在则已经注册过了
        User user1 = userDao.queryByName(user);
        if (user1 != null) {
            return false;
        }
        //不存在，则注册

        userDao.addUser(user);
        return true;
    }

    @Override
    public List<User> queryByInfo(UserQueryObj userQueryObj) {
        List<User> users = userDao.queryByInfo(userQueryObj);
        return users;
    }

    @Override
    public PageInfo<User> getPageInfo(UserQueryObj userQueryObj) {
        //1.创建分页对象
        PageInfo<User> pageInfo = new PageInfo<>();
        //2.封装分页对象
        //用户列表
        List<User> users = userDao.queryByInfo(userQueryObj);
        pageInfo.setList(users);
        //当前页
        pageInfo.setCurrentPage(userQueryObj.getCurrentPage());
        //每页条数
        pageInfo.setPageSize(userQueryObj.getPageSize());
        //查询总条数
        int count = userDao.count(userQueryObj);
        pageInfo.setCount(count);
        //总页数，通过计算得到
        int totalPage = (count - 1) / userQueryObj.getPageSize() + 1;
        pageInfo.setTotalPage(totalPage);
        return pageInfo;
    }
}
