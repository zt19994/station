<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<div align="center">
    <h1>车票列表</h1>
</div>

<div align="center">
    <table id="ticketList" cellspacing="1" border="1">
        <tr>
            <td>编号</td>
            <td>起始站</td>
            <td>终点站</td>
            <td>发车时间</td>
            <td>票价</td>
            <td>里程(未关联)</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${tickets}" var="ticket">
            <tr>
                <td>${ticket.id}</td>
                <td>${ticket.startStation}</td>
                <td>${ticket.stopStation}</td>
                <td>${ticket.departureTime}</td>
                <td>${ticket.price}</td>
                <td>${ticket.routeId}</td>
                <td>
                    <form action="#" method="post">
                        <button type="submit">购买</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="7">
                <a href="#">首页</a>
                <a href="#">上一页</a>
                <a href="#">下一页</a>
                <a href="#">末页</a>
                <span>第 1/2 页，每页条数 5 条，共 7 条</span>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
