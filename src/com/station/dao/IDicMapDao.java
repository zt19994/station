package com.station.dao;

import com.station.entity.Dic;

import java.util.List;

/**
 * 数据字典
 */
public interface IDicMapDao {
    /**
     * 获取字典列表
     * @return
     */
    List<Dic> getList();

    /**
     * 根据 key 获取字典值
     * @param dic
     * @return
     */
    Dic getDic(Dic dic);
}
