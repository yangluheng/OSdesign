<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 杨路恒
  Date: 2019/12/1 0001
  Time: 14:56
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>进程调度结果</title>
</head>
<body>
<center>
    <font color="aqua">您选择的算法:</font>${button}<br>
    <font color="aqua">系统平均周转时间:</font>${averageRoundTime}<br>
    <font color="aqua">系统平均带权周转时间:</font>${averageWeightRoundTime}<br>
        <table border="1">
            <tr bgcolor="aqua">
                <th>进程名</th>
                <th>到达时间</th>
                <th>服务时间</th>
                <th>完成时间</th>
                <th>周转时间</th>
                <th>带权周转时间</th>
            </tr>
            <c:forEach var="process" items="${processesSort}" varStatus="vs">
                <tr>
                    <td>P${process.name}</td>
                    <td>${process.arriveTime}</td>
                    <td>${process.serveTime}</td>
                    <td>${process.finishTime}</td>
                    <td>${process.roundTime}</td>
                    <td>${process.aveRoundTime}</td>
                </tr>
            </c:forEach>

        </table>

</center>
</body>
</html>
