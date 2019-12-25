<%--
  Created by IntelliJ IDEA.
  User: 杨路恒
  Date: 2019/12/15 0015
  Time: 20:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>动态内存分配与回收系统</title>
</head>
<body>
<center>
    <form action="${pageContext.request.contextPath}/MemoryServlet" method="post">
        <h1 style="background: #00FFFF">欢迎进入操作系统动态内存分配与回收系统，请您先输入系统内存大小:</h1>
        系统内存:<input type="text" name="memorySize">
        <input type="submit" value="提交">
    </form>
</center>
</body>
</html>
