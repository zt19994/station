package com.day_28.station.test;

import com.day_28.station.dao.IResourceDao;
import com.day_28.station.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class ResourceTest {
    @Autowired
    private IResourceDao resourceDao;

    /**
    * 测试查询用户权限
    */
    @Test
    public void testGetResource(){
        List<Resource> resourceList = resourceDao.getResourceList(5);
        for (Resource resource : resourceList) {
            System.out.println(resource);
        }
    }
}
