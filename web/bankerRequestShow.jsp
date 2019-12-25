<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 杨路恒
  Date: 2019/12/22 0022
  Time: 14:10
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>银行家算法系统结果</title>
    <script type="text/javascript" src="js/test.js">
    </script>
</head>
<body onload="startSecond();">
<center>
    <%
        String resource_num= (String) request.getSession().getAttribute("resourceNum");
    %>
    <table border="1" width="600px">
            <th bgcolor="aqua">进程</th>
            <th colspan="<%=resource_num%>">Work</th>
            <th colspan="<%=resource_num%>">Allocation</th>
            <th colspan="<%=resource_num%>">Need</th>
            <th colspan="<%=resource_num%>">Work+Allocation</th>
            <th>Finish</th>
        <%
                    String process_num= (String) request.getSession().getAttribute("processNum");
                    Object Allocation=request.getSession().getAttribute("allocation");
                    int[][] allocation= (int[][]) Allocation;
                    Object Need= request.getSession().getAttribute("need");
                    int[][] need= (int[][]) Need;
                    Object WorkAndAllocation= request.getSession().getAttribute("workAndAllocation");
                    int[][] workAndAllocation= (int[][]) WorkAndAllocation;
                    Object Works= request.getSession().getAttribute("Work");
                    int[][] Work= (int[][]) Works;
                    Object Security=request.getSession().getAttribute("security");
                    int[] security= (int[]) Security;
                    int resourceNum=Integer.parseInt(resource_num);
                    int processNum=Integer.parseInt(process_num);
                    boolean[] finish=new boolean[processNum];
                    for (int i = 0; i <finish.length ; i++) {
                        finish[i]=true;
                    }
                    for (int i = 0; i <processNum ; i++) {
                        if (security[i] == -1){
                    %>
        <td bgcolor="red">进程P<%=security[1]%>已经超出最大需求need,系统自动为您跳转上一页面</td>
        <%
                response.setHeader("refresh","1;URL=http://localhost:8080/ProcessScheduling_war_exploded/banker.jsp");
                return;
            }
            if (security[i] == -2){
        %>

        <td bgcolor="red">当前没有足够的资源可以分配，进程P<%=security[1]%>需要等待,系统自动为您跳转上一页面</td>
        <%
                response.setHeader("refresh","1;URL=http://localhost:8080/ProcessScheduling_war_exploded/banker.jsp");
                return;
            }
            if (security[i] == -3){
        %>
        <td bgcolor="red">此时不安全，因为不存在安全序列,自动跳转上一页面</td>
        <%--<a href="bankerShow.jsp"><span id="second">1</span>请您回到初始界面重新输入!</a>--%>
        <%
                        response.setHeader("refresh","1;URL=http://localhost:8080/ProcessScheduling_war_exploded/banker.jsp");
                    return;
                }


                        %>
                    <tr>
                       <td>P<%=security[i]%></td>
                        <%
                for (int j = 0; j <resourceNum ; j++) {
                    %>

            <td bgcolor="aqua"> <%=Work[security[i]][j]%></td>


        <%
                }
                for (int j = 0; j <resourceNum ; j++) {
                    %>
                        <td bgcolor="#7fffd4"><%=allocation[security[i]][j]%> </td>
                        <%
                }
                for (int j = 0; j <resourceNum ; j++) {
                    %>
                        <td bgcolor="#deb887"> <%=need[security[i]][j]%></td>
                        <%
                }
                            for (int j = 0; j <resourceNum ; j++) {
                                %>
                        <td bgcolor="green"> <%=workAndAllocation[security[i]][j]%></td>
                        <%
                            }
                %>
                        <td bgcolor="green"> <%=finish[i]%></td>
                    </tr>
                            <%
            }
        %>
        <%--<th bgcolor="aqua">进程</th>--%>
        <%--<th colspan="<%=resource_num%>">Allocation</th>--%>
        <%--<th colspan="<%=resource_num%>">Need</th>--%>
        <%--<tr>--%>
        <%--<%--%>
        <%--for(int i = 0; i <processNum ; i++) {--%>
            <%--%>--%>
            <%--<td>P<%=i%></td>--%>
            <%--<%--%>
        <%--for (int j = 0; j <resourceNum ; j++) {--%>
                                       <%--%>--%>
        <%--<td bgcolor="#7fffd4"><%=allocation[i][j]%> </td>--%>
            <%--<%--%>
                <%--}--%>
                <%--for (int j = 0; j <resourceNum ; j++) {--%>
                    <%--%>--%>
        <%--<td bgcolor="#deb887"> <%=need[i][j]%></td>--%>
            <%--<%--%>
                <%--}%>--%>
        <%--</tr>--%>
            <%--<%--%>
                <%--}%>--%>
    </table>
    <br>
    此时，存在安全序列:<br>
    <div style="color: green">
        <c:forEach var="p" items="${security}">
            P${p}&nbsp;&nbsp;
        </c:forEach>
    </div>
    Available:
    <div style="color: green">
        <c:forEach var="r" items="${resources}" varStatus="vs">
            R${vs.index+1}:${r}&nbsp;&nbsp;
        </c:forEach>
    </div>
    <form action="${pageContext.request.contextPath}/BankerAlgorithmServlet" method="post">
        <a href="bankerNowShow.jsp">查看当前系统进程资源分布</a><br>
        请输入要请求的进程(0-${processNum-1}):<input type="text" name="requestProcessId" size="10"><br>
        设置进程请求资源数:<br>
        <%
            for (int i = 0; i <resourceNum ; i++) {
        %>
        资源R<%=i+1%>:<input type="text" name="requestNum" size="10">
        <%
            }
        %>
        <br>
        <a href="bankerIndex.jsp">返回银行家算法系统主界面</a>
        <a href="index.jsp">返回课程设计系统主界面</a><br>
        <input type="submit" value="提交">
    </form>
</center>
</body>
</html>
