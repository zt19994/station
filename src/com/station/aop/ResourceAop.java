package com.station.aop;

import com.station.commons.map.MemcachedDicMap;
import com.station.entity.Resource;
import com.station.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ResourceAop {
    private static final Logger logger = LoggerFactory.getLogger(ResourceAop.class);

    public void beforeResource() throws IOException {
        logger.info("------beforeResource------");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        //获取session，查看是否登录
        HttpSession session = request.getSession();
        User loginInSession = (User) session.getAttribute("LOGIN_IN_SESSION");
        if (loginInSession != null) {
            logger.info("已经登录-ResourceAop");
            //登录之后检查权限

            //1.获取当前资源
            String requestURI = request.getRequestURI();
            logger.info("requestURI" + requestURI);
            //2.获取用户所有资源
            String key2 = "Resource_" + loginInSession.getId();
            logger.info("key2：" + key2);
            List<Resource> resources = MemcachedDicMap.getResourceMap(key2);
            logger.info("resources：" + resources);
            //3.比较是否有该资源
            boolean isResource = false;//给定默认的值为没有改权限
            for (Resource resource : resources) {
                String valueResource = resource.getResourceDetail();
                if (requestURI.equals(valueResource)) {
                    //拥有在资源的权限
                    isResource = true;
                    logger.info("有该权限:=" + valueResource);
                    break;
                }
            }
            //没有该资源权限
            if (!isResource) {
                logger.info("无该权限:=" +requestURI);
                response.sendRedirect("/authority.jsp");
            }

        } else {
            //用户登录
        }
    }
}
