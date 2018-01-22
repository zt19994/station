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
                <input type="text" id="userName" placeholder="请输入用户名" value="">
            </td>
        </tr>
        <tr>
            <td>密 &nbsp; 码:</td>
            <td>
                <input type="password" id="password" placeholder="请输入密码" value="">
            </td>
        </tr>
        <tr>
            <td>确认密码:</td>
            <td>
                <input type="password" id="confirmPassword" placeholder="确认密码" value="">
            </td>
        </tr>
        <tr>
            <td>电话号码:</td>
            <td>
                <input type="text" id="phone" placeholder="请输入电话号码" value="">
            </td>
        </tr>
        <tr>
            <td>身份证号:</td>
            <td>
                <input type="text" id="identityCard" placeholder="请输入身份证号" value="">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button onclick="register()">注册</button>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button onclick="login()">已经注册，立即登录</button>
            </td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript">
    function login() {
        location.href="/login/toLogin";
    }

    function register() {
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
        var url = '/register/checkRegister';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',
            success: function (data) {

                if (data.code=='0000'){
                    alert(data.msg);
                    //注册成功，跳转到登录页面
                    window.location.href='/login/toLogin';
                }else {
                    alert(data.msg);
                    //注册失败，请重新注册
                    window.location.href='/register/toRegister';
                }
            },
            error: function (data) {
                alert("失败啦");
            }
        });
    }
</script>
</html>
