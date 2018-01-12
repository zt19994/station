package com.station.dao;

import com.station.entity.Resource;

import java.util.List;

public interface IResourceDao {

    /**
     * 获取用户资源列表
     */
    List<Resource> getResourceList(Integer userId);
}
