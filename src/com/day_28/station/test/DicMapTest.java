package com.day_28.station.test;

import com.day_28.station.dao.IDicMapDao;
import com.day_28.station.entity.Dic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class DicMapTest {
    @Autowired
    private IDicMapDao dicMapDao;

    /**
    * 测试查询数据字典
    */
    @Test
    public void testGetList(){
        List<Dic> list = dicMapDao.getList();
        for (Dic dic : list) {
            System.out.println(dic);
        }
    }
}
