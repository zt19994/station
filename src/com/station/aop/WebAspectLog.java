package com.station.aop;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

/**
 * 日志设置
 */
public class WebAspectLog {
    private static final Logger logger = LoggerFactory.getLogger(WebAspectLog.class);

    public void beforeLog() {
        logger.info("------beforeLog------");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //url地址
        String requestURI = request.getRequestURI();
        logger.info("[requestURI=" + requestURI + "]");
        //URL
        StringBuffer requestURL = request.getRequestURL();
        logger.info("[requestURL=" + requestURL + "]");
        //ip地址
        String remoteAddr = request.getRemoteAddr();
        logger.info("[remoteAddr = " + remoteAddr + "]");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            logger.info("[" + key + "=" + value + "]");
        }
        Date date = new Date();
        logger.info("[ date = " + date + "]");

    }

    public void afterLog(Object returnObject) {
        Date date = new Date();
        logger.info("[ date = " + date + "]");
        String s = JSON.toJSONString(returnObject);
        logger.info("[ returnObject = " + s + "]");
    }
}
