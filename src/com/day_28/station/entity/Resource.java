package com.day_28.station.entity;

import java.io.Serializable;

/**
 * 资源类
 */
public class Resource implements Serializable {
    private Integer userId;
    private String resourceDetail;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getResourceDetail() {
        return resourceDetail;
    }

    public void setResourceDetail(String resourceDetail) {
        this.resourceDetail = resourceDetail;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "userId=" + userId +
                ", resourceDetail='" + resourceDetail + '\'' +
                '}';
    }
}
