<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 杨路恒
  Date: 2019/12/15 0015
  Time: 23:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>内存分配结果</title>
</head>
<body>
<center>
    <table border="1" width="600px">
        <tr bgcolor="aqua">
            <th>编号</th>
            <th>始址</th>
            <th>大小</th>
            <th>空闲状态</th>
        </tr>
        <c:forEach var="zone" items="${zones}" varStatus="vs">
            <tr>
                <td>${vs.index}</td>
                <td>${zone.head}</td>
                <td>${zone.zoneSize}</td>
                <td>${zone.free}</td>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/MemoryAllocationServlet" method="post">
        请您输入要分配的进程大小:<input type="text" name="size"><br>
        请选择您要执行的动态内存分配与回收算法:<br>
        内存回收:<input type="radio" value="Collection" name="button">请输入要回收的内存编号:<input type="text" name="id"><br>
        FF:<input type="radio" value="FF" name="button"><br>
        NF:<input type="radio" value="NF" name="button"><br>
        BF:<input type="radio" value="BF" name="button"><br>
        WF:<input type="radio" value="WF" name="button"><br>
        <input type="submit" value="提交"><br>
        <a href="memoryIndex.jsp">返回动态内存分配与回收算法系统首页</a>&nbsp;&nbsp;&nbsp;
        <b></b>
        <a href="index.jsp">返回操作系统课程设计首页</a>
    </form>
</center>
</body>
</html>
