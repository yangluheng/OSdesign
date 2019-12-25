<%--
  Created by IntelliJ IDEA.
  User: 杨路恒
  Date: 2019/12/18 0018
  Time: 23:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>银行家算法系统</title>
</head>
<body>
<center>
    <form action="${pageContext.request.contextPath}/BankerSecurityServlet" method="post">
        您总共要输入${resourceNum}类资源数量:<br>
        <%
            int resourceNum= Integer.valueOf(String.valueOf(request.getSession().getAttribute("resourceNum")));
            for (int i = 0; i <resourceNum ; i++) {
        %>
        资源R<%=i+1%>:<input type="text" name="resources" size="10">
        <%
            }
        %>
        <br>
        您总共要输入${processNum}个进程信息:<br>
        <%
            int processNum= Integer.parseInt(String.valueOf(request.getSession().getAttribute("processNum")));
            for (int i = 0; i <processNum ; i++) {
                %>
        进程P<%=i+1%>&nbsp;&nbsp;Max:<br>
        <%
                for (int j = 0; j <resourceNum ; j++) {
        %>
        资源R<%=j+1%>:<input type="text" name="maxP<%=i%>" size="10">
        <%
                }
                %><br>
         <%   }
        %><br>

        <%
        for (int i = 0; i <processNum ; i++) {
                                      %>
        进程P<%=i+1%>&nbsp;&nbsp;Allocation:<br>
            <%
                for (int j = 0; j <resourceNum ; j++) {
        %>
        资源R<%=j+1%>:<input type="text" name="allocationP<%=i%>" size="10">
            <%
                }
                %><br>
        <%
            }
        %><br>
        <%--请输入要请求的进程(0-${processNum-1}):<input type="text" name="requestProcessId" size="10"><br>--%>
        <%--设置进程请求资源数:<br>--%>
        <%--<%--%>
            <%--for (int i = 0; i <resourceNum ; i++) {--%>
        <%--%>--%>
        <%--资源R<%=i+1%>:<input type="text" name="requestNum" size="10">--%>
        <%--<%--%>
            <%--}--%>
        <%--%>--%>
        <input type="submit" value="安全性检测">
    </form>
</center>
</body>
</html>
