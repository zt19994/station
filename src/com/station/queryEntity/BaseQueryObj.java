package com.station.queryEntity;

public class BaseQueryObj {
    //当前页,默认为首页
    private Integer currentPage = 1;
    //每页条数
    private Integer pageSize = 4;
    //开始索引
    private Integer startIndex = 0;

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

    public Integer getStartIndex() {
        startIndex = (currentPage - 1) * pageSize;
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
