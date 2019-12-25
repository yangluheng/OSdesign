<%--
  Created by IntelliJ IDEA.
  User: 杨路恒
  Date: 2019/12/15 0015
  Time: 20:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>银行家算法系统</title>
</head>
<body>
<center>
    <form action="${pageContext.request.contextPath}/BankerServlet" method="post">
        <h1 style="background: #00FFFF">欢迎进入操作系统银行家算法系统,请您先输入系统资源种类数与进程数:</h1>
        进程个数:<input type="text" name="processNum">
        资源种类数:<input type="text" name="resourceNum">
        <input type="submit" value="提交">
    </form>
</center>

</body>
</html>
