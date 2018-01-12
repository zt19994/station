package com.station.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {
    //post请求
    public static String post(String url) throws IOException {
        //1.创建一个请求
        HttpPost httpPost = new HttpPost(url);
        //2.创建客户端
        DefaultHttpClient client = new DefaultHttpClient();
        //3.发送请求
        HttpResponse execute = client.execute(httpPost);
        //4.解析数据
        HttpEntity entity = execute.getEntity();
        String s = EntityUtils.toString(entity);
        return s;
    }
}
