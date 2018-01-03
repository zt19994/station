<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2017/12/29
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>更新用户页面</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
</head>
<body>
<h1 align="center" id="title">更新用户页面</h1>
<div align="center">
    <%--private String userName;
    private String password;
    private Integer phone;
    private Integer identityCard;--%>
    <table>
        <tr>
            <td>编号:</td>
            <td>
                <input id="id" type="text" value="${id}" readonly>
            </td>
        </tr>
        <tr>
            <td>用户名:</td>
            <td>
                <input id="userName" type="text" value="">
            </td>
        </tr>
        <tr>
            <td>密码:</td>
            <td>
                <input id="password" type="text" value="">
            </td>
        </tr>
        <tr>
            <td>电话号码:</td>
            <td>
                <input id="phone" type="text" value="">
            </td>
        </tr>
        <tr>
            <td>身份证号:</td>
            <td>
                <input id="identityCard" type="text" value="">
            </td>
        </tr>
        <tr>
            <td>状态:</td>
            <td>
                <input id="state" type="text" value="">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button id="btn" onclick="updateData()">修改</button>
            </td>
        </tr>

    </table>

</div>
</body>
<script type="text/javascript">
    function updateData() {
        //alert("updateData");
        //取出数据
        var id = $("#id").val();
        var userName = $("#userName").val();
        var password = $("#password").val();
        var phone = $("#phone").val();
        var identityCard = $("#identityCard").val();
        var state = $("#state").val();
        var params = {
            id:id,
            userName:userName,
            password:password,
            phone:phone,
            identityCard:identityCard,
            state:state

        };
        var url = 'http://localhost:8080/manage/login/updateData';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',
            success: function (data) {
                //alert("成功啦");
                //跳转到用户列表页面
                location.href='http://localhost:8080/manage/login/userList';
            },
            error: function (data) {
                alert("失败啦");
            }
        });
    }

    /*页面加载后回显数据*/

    function loadUpdateUserData() {
        //alert("loadUpdateUserData");
        //获取id
        var id = $("#id").val();
        //alert(id);
        if (id==null || id.trim()==""){
            //id为空，新增,修改title和button显示
            $("#title").html("新增用户");
            $("#btn").html("新增")

        }
        var params = {
            id:id
        };
        var url = 'http://localhost:8080/manage/login/getUserDataById';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',
            success: function (data) {
                //成功时，取出data
                var user = data.data;
                //从user中取出用户信息
                var userName = user.userName;
                var password = user.password;
                var phone = user.phone;
                var identityCard = user.identityCard;
                var state = user.state;
                //填充值
                $("#userName").val(userName);
                $("#password").val(password);
                $("#phone").val(phone);
                $("#identityCard").val(identityCard);
                $("#state").val(state);
                //alert("成功啦");
            },
            error: function (data) {
                //alert("失败啦");
            }
        });
    }

    loadUpdateUserData();
</script>
</html>
