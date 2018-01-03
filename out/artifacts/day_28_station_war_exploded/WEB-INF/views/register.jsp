<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2017/12/29
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>注册</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
</head>
<body>
<h1 align="center">注册</h1>
<div align="center">
    <table>
        <tr>
            <td>用 户 名:</td>
            <td>
                <input type="text" id="userName" value="">
            </td>
        </tr>
        <tr>
            <td>密  码 &nbsp;:</td>
            <td>
                <input type="password" id="password" value="">
            </td>
        </tr>
        <tr>
            <td>确认密码:</td>
            <td>
                <input type="password" id="confirmPassword" value="">
            </td>
        </tr>
        <tr>
            <td>电话号码:</td>
            <td>
                <input type="text" id="phone" value="">
            </td>
        </tr>
        <tr>
            <td>身份证号:</td>
            <td>
                <input type="text" id="identityCard" value="">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button onclick="register()">注册</button>
                <button onclick="login()">登录</button>
            </td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript">
    //登录
    function login() {
        //alert("login");
        location.href="http://localhost:8080/login/toLogin";
    }

    function register() {
        //alert("logon");
        //1.获取参数
        var userName = $("#userName").val();
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        if (password!=confirmPassword){
            alert("密码不一致");
            return false;
        }
        var phone = $("#phone").val();
        var identityCard = $("#identityCard").val();

        var params = {
            userName:userName,
            password:password,
            phone:phone,
            identityCard:identityCard
        };
        var url = 'http://localhost:8080/register/checkRegister';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',
            success: function (data) {
                //alert("成功啦");

                if (data.code=='0000'){
                    alert(data.msg);
                    //注册成功，跳转到登录页面
                    location.href='http://localhost:8080/login/toLogin';
                }else {
                    alert(data.msg);
                    //注册失败，请重新注册
                    location.href='http://localhost:8080/register/toRegister';
                }
            },
            error: function (data) {
                alert("失败啦");
            }
        });
    }
</script>
</html>
