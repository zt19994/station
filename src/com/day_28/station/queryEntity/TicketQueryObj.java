package com.day_28.station.queryEntity;

public class TicketQueryObj {
    //当前页,默认为首页
    private Integer currentPage=1;
    //每页条数
    private Integer pageSize=4;
    //开始索引
    private Integer startIndex=0;

    private String startStation;
    private String stopStation;

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
        startIndex = (currentPage-1)*pageSize;
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getStopStation() {
        return stopStation;
    }

    public void setStopStation(String stopStation) {
        this.stopStation = stopStation;
    }

    @Override
    public String toString() {
        return "TicketQueryObj{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", startStation='" + startStation + '\'' +
                ", stopStation='" + stopStation + '\'' +
                '}';
    }
}
