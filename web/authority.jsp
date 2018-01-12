<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/10
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>无权限</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
</head>
<body>
<div>
    <h1 align="center">无权限</h1>
</div>
<div align="center">
    <button onclick="login()">登录</button>

</div>
</body>
<script type="text/javascript">
    //登录
    function login() {
        //alert("login");
        location.href="http://localhost:8080/login/toLogin";
    }
</script>
</html>
