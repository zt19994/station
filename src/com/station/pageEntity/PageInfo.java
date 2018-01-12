package com.station.pageEntity;

import java.util.List;

/**
 * 页面显示信息，用于分页
 */
public class PageInfo<T> {
    //ticket列表
    private List<T> list;
    //当前页
    private Integer currentPage;
    //每页条数
    private Integer pageSize;
    //总条数
    private Integer count;
    //总页数
    private Integer totalPage;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }


}
