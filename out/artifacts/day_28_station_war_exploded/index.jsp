<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2017/12/27
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <head>
    <title>导航页面</title>
        <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
    </head>
    <body>
    <h1 align="center">导航页面</h1>
    <div align="center">
        <button onclick="ticket2()">车票列表</button>
        <br/>
        <button onclick="userList()">用户列表</button>
        <br>
        <button onclick="login()">登录</button>
        <button onclick="register()">注册</button>
    </div>
    </body>
<script type="text/javascript">
    function ticket2() {
        location.href="http://localhost:8080//ticket2/page";
    }


    function userList() {
        location.href="http://localhost:8080/manage/login/userList";
    }

    //登录
    function login() {
        //alert("login");
        location.href="http://localhost:8080/login/toLogin";
    }

    //注册
    function register() {
        //alert("logon");
        location.href="http://localhost:8080/register/toRegister";
    }
</script>
</html>
