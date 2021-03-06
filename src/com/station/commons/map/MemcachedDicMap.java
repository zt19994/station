package com.station.commons.map;

import com.station.dao.IDicMapDao;
import com.station.entity.Dic;
import com.station.entity.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Memcached数据字典
 */
public class MemcachedDicMap {

    private static IDicMapDao dicMapDao;

    private static MemcachedAccess memcachedAccess;


    static {
        //获取应用上下文对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        //获取dicDao实例
        dicMapDao = ctx.getBean(IDicMapDao.class);
        //获取memcached
        memcachedAccess = (MemcachedAccess) ctx.getBean(MemcachedAccess.class);
        //调用方法初始化字典
        addMapValue();
    }

    public static String getFieldDetailByMemcachedAccess(String tableName, String fieldName, String filedValue) {
        String key = tableName + "_" + fieldName + "_" + filedValue;
        String value = memcachedAccess.get(key);
        if (value == null) { //如果 value 为空 重新查询数据库
            Dic dicQuery = new Dic();
            //封装字典查询对象
            dicQuery.setTableName(tableName);
            dicQuery.setFieldName(fieldName);
            dicQuery.setFieldValue(filedValue);
            Dic dic = dicMapDao.getDic(dicQuery);
            if (dic != null) {//数据有该值
                String fieldDescribe = dic.getFieldDescribe();
                memcachedAccess.add(key, 1*60*24, fieldDescribe);
                return fieldDescribe;
            }
            value = "暂无";
        }
        return value;
    }

    /**
     * 初始化字典数据
     */
    private static void addMapValue() {
        List<Dic> list = dicMapDao.getList();
        for (int i = 0; i < list.size(); i++) {
            Dic dic = list.get(i);
            String tableName = dic.getTableName();
            String fieldName = dic.getFieldName();
            String fieldValue = dic.getFieldValue();
            String key = tableName + "_" + fieldName + "_" + fieldValue;
            String fieldDescribe = dic.getFieldDescribe();
            memcachedAccess.add(key, 1*60*24, fieldDescribe);
        }

    }


    /**
     * 把资源放入缓存
     * @param key
     * @param value
     */
    public static void putResourceMap(String key,Object value) {

            memcachedAccess.addResourceValue(key, value);
        }


    /**
     * 获取资源
     * @param key
     * @return
     */
    public static List<Resource> getResourceMap(String key) {
        List<Resource> value =(List<Resource>) memcachedAccess.getResourceValue(key);
        return value;
    }

}

