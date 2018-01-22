package com.station.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.station.commons.map.MemcachedDicMap;
import com.station.dao.IResourceDao;
import com.station.dao.IUserDao;
import com.station.entity.Resource;
import com.station.entity.User;
import com.station.pageEntity.PageInfo;
import com.station.queryEntity.UserQueryObj;
import com.station.service.IUserService;
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
    public boolean addUser(User user) {
        user.setState(0);
        int resultCount = userDao.addUser(user);
        return resultCount > 0;
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
        return userDao.queryById(id);
    }

    @Override
    public User queryByName(User user) {
        return userDao.queryByName(user);
    }

    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
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
        return userDao.queryByInfo(userQueryObj);
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
