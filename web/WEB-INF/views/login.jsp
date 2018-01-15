<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2017/12/29
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录页面</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
</head>
<body>
<h1 align="center">登录页面</h1>
<div align="center">
    <table>
        <tr>
            <td>
                <input id="loginToken" type="hidden" value="${loginToken}">
            </td>
        </tr>
        <tr>
            <td>用户名:</td>
            <td>
                <input id="userName" type="text" placeholder="请输入用户名" value="">
            </td>
        </tr>
        <tr>
            <td>密&nbsp;码:</td>
            <td>
                <input id="password" type="password" placeholder="请输入密码" value="">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button onclick="login()">登录</button>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button onclick="toRegister()">还未注册，立即注册</button>
            </td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript">
    //跳转到注册
    function toRegister() {
        //alert("logon");
        location.href = "http://localhost:8080/register/toRegister";
    }

    function login() {
        //alert("login");
        //1.获取参数
        var userName = $("#userName").val();
        var password = $("#password").val();
        //获取loginToken
        var loginToken = $("#loginToken").val();
        var params = {
            userName:userName,
            password:password,
            loginToken:loginToken
        };
        var url = 'http://localhost:8080/login/checkLogin';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',
            success: function (data) {
                //alert("成功啦");
                if(data.code=="0000"){
                    location.href="http://localhost:8080/ticket2/page";
                }else {
                    alert(data.msg);
                    location.href="http://localhost:8080/login/toLogin";
                }
            },
            error: function (data) {
                alert("失败啦");
            }
        });
    }
</script>
</html>
