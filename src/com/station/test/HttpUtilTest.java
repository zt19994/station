package com.station.test;

import com.station.entity.Ticket;
import com.station.util.HttpClientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

public class HttpUtilTest {
    
    /**
    * 测试站间互售
    */
    @Test
    public void testHttpUtil() throws IOException {
        String url = "http://127.0.0.1:8080/ticket2/query2?token=123456";
        //调用httpClient发送请求
        String post = HttpClientUtil.post(url);
        //解析json格式
        //1.将json字符串转换为json对象
        JSONObject jsonObject = JSONObject.fromObject(post);
        String string = jsonObject.toString();
        //获取code
        System.out.println(string);
        String code = jsonObject.getString("code");
        System.out.println(code);
        //获取success
        boolean success = jsonObject.getBoolean("success");
        System.out.println(success);
        //获取data
        JSONObject data = jsonObject.getJSONObject("data");
        //获取data中的list
        JSONArray list = data.getJSONArray("list");
        for (int i=0;i<list.size();i++) {
            JSONObject jsonObject1 = list.getJSONObject(i);
            int id = jsonObject1.getInt("id");
            String startStation = jsonObject1.getString("startStation");
            String stopStation = jsonObject1.getString("stopStation");
            String departureTime = jsonObject1.getString("departureTime");
            double price = jsonObject1.getDouble("price");
            int ticketNum = jsonObject1.getInt("ticketNum");

            Ticket ticket = new Ticket();
            ticket.setId(id);
            ticket.setStartStation(startStation);
            ticket.setStopStation(stopStation);
            ticket.setDepartureTime(departureTime);
            ticket.setPrice(price);
            ticket.setTicketNum(ticketNum);
            System.out.println(ticket);
        }

        System.out.println("post: " + "");

    }

    /**
    * 测试外网地址
    */
    @Test
    public void test2() throws IOException {
        String url = "http://10.3.2.154:8080/ticket/data2";
        //调用httpClient发送请求
        String post = HttpClientUtil.post(url);
        System.out.println(post);
        JSONObject jsonObject = JSONObject.fromObject(post);
        int currentPage = jsonObject.getInt("currentPage");
        System.out.println(currentPage);
        int totalPage = jsonObject.getInt("totalPage");
        System.out.println(totalPage);
        int pageSize = jsonObject.getInt("pageSize");
        System.out.println(pageSize);
        int count = jsonObject.getInt("count");
        System.out.println(count);
        JSONArray list = jsonObject.getJSONArray("list");
        System.out.println(list);
    }

    /**
    *
    */
    @Test
    public void test4() throws IOException {
        String url = "http://10.3.2.154:8080/ticket/data";
        //调用httpClient发送请求
        String post = HttpClientUtil.post(url);
        String post1 = "{list:" + post + "}";
        JSONObject jsonObject = JSONObject.fromObject(post1);
        JSONArray list = jsonObject.getJSONArray("list");
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject1 = list.getJSONObject(i);
            int id = jsonObject1.getInt("id");
            String startStation = jsonObject1.getString("startStation");
            String stopStation = jsonObject1.getString("stopStation");
            String departureTime = jsonObject1.getString("departureTime");
            String typeName = jsonObject1.getString("typeName");
            double price = jsonObject1.getDouble("price");
            int ticketNum = jsonObject1.getInt("surplusVote");

            Ticket ticket = new Ticket();
            ticket.setId(id);
            ticket.setStartStation(startStation);
            ticket.setStopStation(stopStation);
            ticket.setDepartureTime(departureTime);
            ticket.setTypeName(typeName);
            ticket.setPrice(price);
            ticket.setTicketNum(ticketNum);
            System.out.println(ticket);

        }
    }

    /**
    *
    */
    @Test
    public void test3() throws IOException {
        String url = "http://10.3.2.165:8080/ticket2/query2?token=123456";
        //调用httpClient发送请求
        String post = HttpClientUtil.post(url);
        JSONObject jsonObject = JSONObject.fromObject(post);
        String string = jsonObject.toString();
        //获取code
        System.out.println(string);
        String code = jsonObject.getString("code");
        System.out.println("code:"+code);
        //获取success
        boolean success = jsonObject.getBoolean("success");
        System.out.println("success"+success);
        //data
        JSONObject data = jsonObject.getJSONObject("data");
        System.out.println("data:"+data);

    }


    /**
    *
    */
    @Test
    public void test5() throws IOException {
        Integer ticketId=2;
        String url = "http://10.3.2.154:8080/ticket/data3?id=" + ticketId + "&token=123";
        System.out.println(url);
        //String post = HttpClientUtil.post(url);

        //System.out.println(post);
    }
}
