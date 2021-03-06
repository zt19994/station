package com.station.commons.map;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import java.util.concurrent.TimeoutException;

public class MemcachedAccess {
    //创建memcached服务器
    private MemcachedClient client;


    public boolean add(String key, int expire, Object value){
        try {
            return client.add(key, expire, value);
        } catch (TimeoutException |InterruptedException|MemcachedException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(String key){

        try {
            return client.get(key);
        } catch (TimeoutException |InterruptedException|MemcachedException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getResourceValue(String key){

        try {
            return client.get(key);
        } catch (TimeoutException |InterruptedException|MemcachedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addResourceValue(String key, Object value){
        try {
            return client.add(key, 1*60*24, value);
        } catch (TimeoutException |InterruptedException|MemcachedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setClient(MemcachedClient client) {
        this.client = client;
    }

    public MemcachedClient getClient() {
        return client;
    }

}
