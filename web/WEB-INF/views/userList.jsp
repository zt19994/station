<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2017/12/27
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>用户列表</title>

    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
<%--验证jquery是否加载成功
    <script type="text/javascript">
        alert($);
    </script>--%>
</head>
<body>
<div align="center">
    <h1>用户列表</h1>
</div>
<div align="center">
    <button onclick="updateUser()">新增</button>
</div>
<br/>
<div align="center">
    <table id="userList" border="1" cellspacing="1" width="600">
        <tr>
            <td>编号</td>
            <td>用户名</td>
            <td>密码</td>
            <td>电话号</td>
            <td>身份证号</td>
            <td>状态</td>
            <td>删除</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
</div>
</body>

<script type="text/javascript">
   /* function addUser() {
        //alert("addUser");
        location.href='http://localhost:8080/register/toRegister';
    }*/

    function loadData(){
        /*alert("loadData");*/
        var params = {

        };
        var url = 'http://localhost:8080/manage/login/userData';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',

            success: function (data) {
                /*alert("成功啦");*/

                /*   /!*测试对象数量*!/
                   var num = data.length;
                   alert(num);*/

                var html =
                    '<tr>'+
                    '<td>编号</td>'+
                    '<td>用户名</td>'+
                    '<td>密码</td>'+
                    '<td>电话号</td>'+
                    '<td>身份证号</td>'+
                    '<td>状态</td>'+
                    '<td>操作</td>'+
                    '</tr>';

                for (var i=0; i<data.length; i++){
                    var user = data[i];
                    var userId = user.id;
                    var userName = user.userName;
                    var password = user.password;
                    var phone = user.phone;
                    var identityCard = user.identityCard;
                    var state = user.state;

                    html = html +
                        '<tr>'+
                        '<td>'+ userId +'</td>'+
                        '<td>'+ userName +'</td>'+
                        '<td>'+ password + '</td>'+
                        '<td>'+ phone + '</td>'+
                        '<td>'+ identityCard + '</td>'+
                        '<td>'+ state + '</td>'+
                        '<td>' +
                        '<button onclick="deleteUser('+userId+')">删除</button>' +
                        '<button onclick="updateUser('+userId+')">修改</button>' +
                        '</td>';
                }
                html = html +'<tr>'+
                    '<td colspan="7">'+
                    '<a href="#">首页</a>&nbsp;'+
                    '<a href="#">上一页</a>&nbsp;'+
                    '<a href="#">下一页</a>&nbsp;'+
                    '<a href="#">末页</a>&nbsp;'+
                    '<span>第 1/2 页，每页条数 5 条，共 7 条</span>'+
                    '</td>'+
                    '</tr>';

                $("#userList").html(html);
            },
            error: function (data) {
                /*alert("失败啦");*/
            }
        });
    }

    function deleteUser(userId) {
        /*alert("deleteUser");*/
        var params = {
            id:userId
        };
        var url = 'http://localhost:8080/manage/login/deleteUser';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',

            success: function (data) {
               /* alert("成功啦");*/
                //跳转到列表页面，即刷新页面
                location.href='http://localhost:8080/manage/login/userList';

            },
            error: function (data) {
                alert("失败啦");
            }
        });
    }

    /*修改和合并一起*/
    function updateUser(userId) {
        if (userId==null){
            location.href='http://localhost:8080//manage/login/toUpdateUser';
        }else {
            //alert("updateUser, userId=" + userId);
            location.href='http://localhost:8080/manage/login/toUpdateUser?id=' + userId;
        }
    }

    loadData();
</script>
</html>
