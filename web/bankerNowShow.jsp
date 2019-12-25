<%--
  Created by IntelliJ IDEA.
  User: 杨路恒
  Date: 2019/12/25 0025
  Time: 15:39
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统当前进程资源</title>
</head>
<body>
<center>
    <table border="1" width="600px">
        <%
            String resource_num= (String) request.getSession().getAttribute("resourceNum");
            String process_num= (String) request.getSession().getAttribute("processNum");
            Object Allocation=request.getSession().getAttribute("allocation");
            int[][] allocation= (int[][]) Allocation;
            Object Need= request.getSession().getAttribute("need");
            int[][] need= (int[][]) Need;
            Object Available=request.getSession().getAttribute("resources");
            int[] available= (int[]) Available;
            Object Max=request.getSession().getAttribute("max");
            int[][] max= (int[][]) Max;
            int resourceNum=Integer.parseInt(resource_num);
            int processNum=Integer.parseInt(process_num);
            %>
            <th bgcolor="aqua">进程</th>
        <th colspan="<%=resource_num%>">Max</th>
        <th colspan="<%=resource_num%>">Allocation</th>
        <th colspan="<%=resource_num%>">Need</th>
        <th colspan="<%=resource_num%>">Available</th>
        <tr>
            <%
                for(int i = 0; i <processNum ; i++) {
            %>
            <td>P<%=i%></td>
            <%
                for (int j = 0; j <resourceNum ; j++) {
            %>
            <td bgcolor="#7fffd4"><%=max[i][j]%> </td>
            <%
                }
                for (int j = 0; j <resourceNum ; j++) {
            %>
            <td bgcolor="#7fffd4"><%=allocation[i][j]%> </td>
            <%
                }
                for (int j = 0; j <resourceNum ; j++) {
            %>
            <td bgcolor="#deb887"> <%=need[i][j]%></td>
            <%
                }
                for (int j = 0; j <resourceNum ; j++) {
            %>
            <td bgcolor="#deb887"> <%=available[j]%></td>
            <%
                }%>
        </tr>
        <%
            }%>
</table>
</center>
</body>
</html>
