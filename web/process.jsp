<%--
  Created by IntelliJ IDEA.
  User: 杨路恒
  Date: 2019/11/30 0030
  Time: 23:08
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>进程信息</title>
</head>
<body>
<center>
    <form action="${pageContext.request.contextPath}/ProcessServlet" method="post">
        您总共要输入${processNum}个进程信息:到达时间		服务时间		优先级<br>
        <%
            int processNum= Integer.parseInt(request.getParameter("processNum"));
            request.setAttribute("processNum",processNum);
            for (int i = 0; i <processNum ; i++) {
        %>
                进程P<%=i+1%>:<input type="text" name="arriveTime"><input type="text" name="serveTime"><input type="text" name="priority"><br>
            <%
            }
            %>
        请您输入系统的时间片:<input type="text" name="sliceTime"><br>
        请选择您要执行的进程调度算法:<br>
        FCFS:<input type="radio" value="FCFS" name="button"><br>
        SJF:<input type="radio" value="SJF" name="button"><br>
        RR:<input type="radio" value="RR" name="button"><br>
        HRRN:<input type="radio" value="HRRN" name="button"><br>
        <input type="submit" value="提交">
        <%--<c:forEach var="num" items="${processNum}" varStatus="vs">--%>
            <%--进程${num}:<input type="text" name="${num}">--%>
        <%--</c:forEach>--%>
    </form>
</center>

</body>
</html>
