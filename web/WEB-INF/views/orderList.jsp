<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/3
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<html>
<head>
    <title>车票订单列表</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
</head>
<body>
<h1 align="center">车票订单列表</h1>
<div align="center">
    起始站：<input id="startStation" type="text" value=""> 到
    终点站：<input id="stopStation" type="text" value="">
    用户名：<input id="userName" type="text" value="">
    <button onclick="loadData()">查询</button>
    <table id="orderList" cellspacing="1" border="1" width="800">
        <tr>
            <td>编号</td>
            <td>起始站</td>
            <td>终点站</td>
            <td>用户名</td>
            <td>购买数量</td>
            <td>订单编号</td>
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
        loadData(1)
    }
    //上一页
    function prePage() {
        //alert("prePage");
        var currentPage = $("#currentPage").html();
        var _currentPage = currentPage - 1;
        if (currentPage<=1){
            _currentPage = 1;
            return _currentPage;
        }
        //alert(_currentPage);
        loadData(_currentPage)
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
        loadData(currentPage)
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
    function loadData(currentPage) {
        //alert("loadData");
        //获取查询参数
        var startStation = $("#startStation").val();
        var stopStation = $("#stopStation").val();
        var userName = $("#userName").val();

        var params = {
            startStation:startStation,
            stopStation:stopStation,
            userName:userName,
            currentPage:currentPage
        };
        var url = 'http://localhost:8080/order/data3';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',

            success: function (data) {
                //alert("成功了");
                //获取数据
                var orderList = data.list;
                var currentPage = data.currentPage;
                var pageSize = data.pageSize;
                var count = data.count;
                var totalPage = data.totalPage;

                var html =
                '<tr>'+
                '<td>编号</td>'+
                '<td>起始站</td>'+
                '<td>终点站</td>'+
                '<td>用户名</td>'+
                '<td>购买数量</td>'+
                '<td>订单编号</td>'+
                '</tr>';

                for (var i=0; i<orderList.length; i++){
                    var orderPage = orderList[i];
                    var id = orderPage.id;
                    var startStation = orderPage.startStation;
                    var stopStation = orderPage.stopStation;
                    var userName = orderPage.userName;
                    var num = orderPage.num;
                    var orderNum = orderPage.orderNum;

                    html = html +
                    '<tr>'+
                    '<td>'+ id +'</td>'+
                    '<td>'+ startStation +'</td>'+
                    '<td>'+ stopStation +'</td>'+
                    '<td>'+ userName +'</td>'+
                    '<td>'+ num +'</td>'+
                    '<td>'+ orderNum +'</td>'+
                    '</tr>';
                }
                $("#orderList").html(html);
                //注入页面信息
                $("#currentPage").html(currentPage);
                $("#pageSize").html(pageSize);
                $("#count").html(count);
                $("#totalPage").html(totalPage);
            },
            error: function (data) {
                alert("失败啦");
            }
        });
    }

    loadData(1);
</script>
</html>
