<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/5
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>站间互售车票列表</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div>
    <h1 align="center">站间互售车票列表</h1>
</div>
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
    function firstPage() {
        var currentPage = 1;
        loadData(3,currentPage);
    }

    function prePage() {
        var currentPage = $("#currentPage").html();
        var _currentPage = currentPage - 1;
        if (currentPage <= 1) {
            _currentPage = 1;
            return _currentPage;
        }
        loadData(3,_currentPage);
    }

    function nextPage() {
        var totalPage = $("#totalPage").html();
        var currentPage = $("#currentPage").html();
        if (currentPage>=totalPage){
            currentPage=totalPage;
            return currentPage;
        }
        var _currentPage = parseInt(currentPage) + 1;
        loadData(3,_currentPage)
    }

    function lastPage() {
        var currentPage = $("#totalPage").html();
        loadData(3,currentPage);
    }

    function jumpPage() {
        var totalPage = $("#totalPage").html();
        var currentPage = $("#jumpPage").val();
        if (currentPage<=1){
            currentPage = 1;
            loadData(3,currentPage);
        }else if (currentPage>=totalPage){
            currentPage=totalPage;
            loadData(3,currentPage);
        }else {
            loadData(3,currentPage);
        }
    }
    function loadData(pageSize, currentPage) {
        var params = {
            pageSize:pageSize,
            currentPage:currentPage
        };
        var url = '/otherStation/loadData';
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


                    html = html +
                        '<tr>' +
                        '<td>' + id + '</td>' +
                        '<td>' + startStation + '</td>' +
                        '<td>' + stopStation + '</td>' +
                        '<td>' + departureTime + '</td>' +
                        '<td>' + price + '</td>' +
                        '<td>' + ticketNum + '</td>' +
                        '<td>' + typeName + '</td>' +
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
        var params = {
            id: id
        };
        var url = '/otherStation/buyOtherTicket';
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
                    window.location.href = "/otherStation/toOtherStationList";
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

    loadData(3,1);
</script>

</html>
