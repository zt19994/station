package com.day_28.station.map;

import com.day_28.station.dao.IDicMapDao;
import com.day_28.station.entity.Dic;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 */
public class DicMap {

    private static IDicMapDao dicMapDao;

    private static Map<String, String> map = new HashMap();

    static {
        //获取应用上下文对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        //获取dicDao实例
        dicMapDao = ctx.getBean(IDicMapDao.class);
        //调用方法初始化字典
        addMapValue();
    }

    public static String getFieldDetail(String tableName, String fieldName, String filedValue) {
        String key = tableName + "_" + fieldName + "_" + filedValue;
        String value = map.get(key);
        if (value == null) { //如果 value 为空 重新查询数据库
            Dic dicQuery = new Dic();
            Dic dic = dicMapDao.getDic(dicQuery);
            if (dic != null) {//数据有该值
                String fieldDescribe = dic.getFieldDescribe();
                map.put(key, fieldDescribe);
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
            map.put(key, fieldDescribe);
        }

    }
}

