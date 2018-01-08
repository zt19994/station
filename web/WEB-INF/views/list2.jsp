<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2017/12/27
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>车票列表</title>
    <%--类型，jquery地址，加/线--%>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js">
    </script>
    <script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
    <%--
    测试jQuery是否加载成功
        <script type="text/javascript">
            alert($);
        </script>--%>

</head>
<body>
<div align="center">
    <h1>车票列表</h1>
</div>
<div align="center">
    <button onclick="logout()">注销</button>
    <button onclick="login()">登录</button>
    <button onclick="register()">注册</button>
    <button onclick="ticketOrder()">查看订单列表</button>
    <span>售票员:${LOGIN_IN_SESSION.userName}</span>
</div>
<br/>
<div align="center">
    <table>
        <tr>
            <td>起 始 站:<input id="startStation" type="text" value=""> 到</td>
            <td>终 点 站:<input id="stopStation" type="text" value=""></td>
        </tr>
        <tr>
            <td>
                开始时间:<input id="minTime" type="text" />
                <img onclick="WdatePicker({el:'minTime'})" src="/My97DatePicker/skin/datePicker.gif" width="20" height="22" align="absmiddle">
            </td>
            <td>
                结束时间:<input id="maxTime" type="text"/>
                <img onclick="WdatePicker({el:'maxTime'})" src="/My97DatePicker/skin/datePicker.gif" width="20" height="22" align="absmiddle">
            </td>
            <td>
                <button onclick="loadData()">查询</button>
            </td>
        </tr>
    </table>

    <br/>
    <table id="ticketList" cellspacing="1" border="1" width="800">
        <tr>
            <td>编号</td>
            <td>起始站</td>
            <td>终点站</td>
            <td>发车时间</td>
            <td>票价</td>
            <td>余票</td>
            <td>类型</td>
            <td>里程(未关联)</td>
            <td>操作</td>
        </tr>
    </table>
    <br/>
    <button onclick="firstPage()">首页</button>
    <button onclick="prePage()">上一页</button>
    <button onclick="nextPage()">下一页</button>
    <button onclick="lastPage()">末页</button>
    跳转到:<input id="jumpPage" type="text" value="1">
    <button onclick="jumpPage()">跳转</button>
    第 <span id="currentPage"></span>/<span id="totalPage"></span> 页，
    分页条数 <span id="pageSize"></span> 条，
    总共 <span id="count"></span> 条

</div>
</body>

<script type="text/javascript">
    //查看订单列表
    function ticketOrder() {
        //alert("ticketOrder");
        location.href = "http://localhost:8080/order/order";
    }

    //注销
    function logout() {
        location.href = "http://localhost:8080/login/logout";
    }


    //登录
    function login() {
        //alert("login");
        location.href = "http://localhost:8080/login/toLogin";
    }

    //注册
    function register() {
        //alert("logon");
        location.href = "http://localhost:8080/register/toRegister";
    }

    //首页
    function firstPage() {
        //alert("firstPage");
        var currentPage = 1;
        loadData(currentPage);
    }

    //上一页
    function prePage() {
        //alert("prePage");
        var currentPage = $("#currentPage").html();
        var _currentPage = currentPage - 1;
        if (currentPage <= 1) {
            _currentPage = 1;
            return _currentPage;
        }
        //alert(_currentPage);
        loadData(_currentPage);
    }

    //下一页
    function nextPage() {
        //alert("nextPage");
        var totalPage = $("#totalPage").html();
        var currentPage = $("#currentPage").html();
        if (currentPage>=totalPage){
            currentPage=totalPage;
            return currentPage;
        }
        var _currentPage = parseInt(currentPage) + 1;
        //alert(_currentPage);
        loadData(_currentPage)
    }
    //末页
    function lastPage() {
        var currentPage = $("#totalPage").html();
        loadData(currentPage);
    }
    //跳转页
    function jumpPage() {
        var totalPage = $("#totalPage").html();
        var currentPage = $("#jumpPage").val();
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


    function loadData(_currentPage) {
        /*把查询数据合成了*/
        var startStation = $("#startStation").val();
        var stopStation = $("#stopStation").val();
        var minTime = $("#minTime").val();
        var maxTime = $("#maxTime").val();
        var params = {
            startStation: startStation,
            stopStation: stopStation,
            currentPage: _currentPage,
            minTime:minTime,
            maxTime:maxTime
        };
        var url = 'http://localhost:8080/ticket2/query2';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',

            success: function (data) {

                var html =
                    '<tr>' +
                    '<td>编号</td>' +
                    '<td>起始站</td>' +
                    '<td>终点站</td>' +
                    '<td>发车时间</td>' +
                    '<td>票价</td>' +
                    '<td>余票</td>' +
                    '<td>类型</td>' +
                    '<td>里程(未关联)</td>' +
                    '<td>操作</td>' +
                    '</tr>';

                var pageInfo = data.data;
                var ticketList = pageInfo.list;
                var currentPage = pageInfo.currentPage;
                var pageSize = pageInfo.pageSize;
                var count = pageInfo.count;
                var totalPage = pageInfo.totalPage;

                for (var i = 0; i < ticketList.length; i++) {
                    var ticket = ticketList[i];
                    var id = ticket.id;
                    var startStation = ticket.startStation;
                    var stopStation = ticket.stopStation;
                    var departureTime = ticket.departureTime;
                    var price = ticket.price;
                    var ticketNum = ticket.ticketNum;
                    var typeName = ticket.typeName;
                    var routeId = ticket.routeId;


                    html = html +
                        '<tr>' +
                        '<td>' + id + '</td>' +
                        '<td>' + startStation + '</td>' +
                        '<td>' + stopStation + '</td>' +
                        '<td>' + departureTime + '</td>' +
                        '<td>' + price + '</td>' +
                        '<td>' + ticketNum + '</td>' +
                        '<td>' + typeName + '</td>' +
                        '<td>' + routeId + '</td>' +
                        '<td align="center">' +
                        '<button onclick="buyTicket(' + id + ')">购买</button>' +
                        '</td>' +
                        '</tr>';
                }
                $("#ticketList").html(html);
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

    function buyTicket(id) {
        //alert("buyTicket");

        var params = {
            id: id
        };
        var url = 'http://localhost:8080/ticket2/buyTicket';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',

            success: function (data) {
                //alert("成功了");
                //购票成功后，返回车票列表页面
                //获取数据
                var code = data.code;
                var msg = data.msg;
                if (code == "0000") {
                    //alert(msg);
                    location.href = "http://localhost:8080/ticket2/page";
                    return false
                } else {
                    //alert(msg);
                }
            },
            error: function (data) {
                alert("失败啦");
            }
        });
    }

    loadData(1);
</script>

</html>
