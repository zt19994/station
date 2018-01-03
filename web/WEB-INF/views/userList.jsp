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
    用户名：<input id="userName" type="text" value="">
    身份证号：<input id="identityCard" type="text" value="">
    电话号码：<input id="phone" type="text" value="">
    <button onclick="loadData()">查询</button>
    <br/>
    <table id="userList" border="1" cellspacing="1" width="800">
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
    <br/>
    <button onclick="firstPage()">首页</button>
    <button onclick="prePage()">上一页</button>
    <button onclick="nextPage()">下一页</button>
    <button onclick="lastPage()">末页</button>
    跳转到:<input id="jumpPage" type="text" value="1" > <button onclick="jumpPage()">跳转</button>
    第 <span id="currentPage"></span>/<span id="totalPage"></span> 页，
    分页条数 <span id="pageSize"></span> 条，
    总共 <span id="count"></span> 条
</div>
</body>

<script type="text/javascript">
    //首页
    function firstPage() {
        //alert("firstPage");
        loadData(1);
    }

    //上一页
    function prePage() {
        //alert("prePage");
        var currentPage = $("#currentPage").html();
        var prePage = currentPage - 1;
        if (currentPage<=1){
            prePage = 1;
            return prePage;
        }
        loadData(prePage);
    }

    //下一页
    function nextPage() {
        //alert("nextPage");
        var currentPage = $("#currentPage").html();
        var totalPage = $("#totalPage").html();
        if (currentPage>=totalPage){
            currentPage = totalPage;
            return currentPage;
        }
        var nextPage = parseInt(currentPage) + 1;
        loadData(nextPage);
    }

    //末页
    function lastPage() {
        var lastPage = $("#totalPage").html();
        //alert("lastPage"+lastPage);
        loadData(lastPage);
    }

    function jumpPage() {
        var totalPage = $("#totalPage").html();
        var currentPage = $("#jumpPage").val();
        //alert(currentPage);
        if (currentPage<=1){
            currentPage = 1;
            loadData(currentPage);
        }else if (currentPage>=totalPage){
            currentPage=totalPage;
            loadData(currentPage);
        }else {
            loadData(currentPage);
        }

    }
   /* function addUser() {
        //alert("addUser");
        location.href='http://localhost:8080/register/toRegister';
    }*/

    function loadData(_currentPage){
        /*alert("loadData");*/
        //获取查询参数
        var userName = $("#userName").val();
        var identityCard = $("#identityCard").val();
        var phone = $("#phone").val();
        var params = {
            userName:userName,
            identityCard:identityCard,
            phone:phone,
            currentPage:_currentPage
        };
        var url = 'http://localhost:8080/manage/login/query2';
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
                var userList = data.list;
                var currentPage = data.currentPage;
                var pageSize = data.pageSize;
                var count = data.count;
                var totalPage = data.totalPage;

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

                for (var i=0; i<userList.length; i++){
                    var user = userList[i];
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

                $("#userList").html(html);
                //注入分页对象的值
                $("#currentPage").html(currentPage);
                $("#pageSize").html(pageSize);
                $("#count").html(count);
                $("#totalPage").html(totalPage);
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

    loadData(1);
</script>
</html>
