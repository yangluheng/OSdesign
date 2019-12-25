<%--
  Created by IntelliJ IDEA.
  User: 杨路恒
  Date: 2019/11/29 0029
  Time: 19:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>操作系统进程调度</title>
    <%--JSP动态增删输入框--%>
    <script type="text/javascript">
        <!--
        var textNumber = 1;
        function addTextBox(form, afterElement) {
            // Increment the textbox number
            textNumber++;
            // Create the label
            var label = document.createElement("label");
            // Create the textbox
            var textField = document.createElement("input");
            textField.setAttribute("type","text");
            textField.setAttribute("name","txt"+textNumber);
            textField.setAttribute("id","txt"+textNumber);
            // Add the label's text
            label.appendChild(document.createTextNode("进程信息:"+textNumber+": "));
            // Put the textbox inside
            label.appendChild(textField);
            // Add it all to the form
            form.insertBefore(label,afterElement);
            return false;
        }
        function removeTextBox(form) {
            if (textNumber > 1) { // If there's more than one text box
                // Remove the last one added
                form.removeChild(document.getElementById("txt"+textNumber).parentNode);
                textNumber--;
            }
            else alert("不能删除");
        }
        //-->
    </script>
    <style type="text/css">
        <!--
        label {display:block;}
        -->
    </style>
</head>
<body>
<center>
    <%--<form id="myForm" method="get" action="./" />--%>
    <%--<label>Text Box #1: <input type="text" name="txt1" id="txt1" /></label>--%>
    <%--<p>--%>
        <%--<input type="button" value="Add Textbox" onClick="addTextBox(this.form,this.parentNode)" />--%>
        <%--<input type="button" value="Remove Textbox" onClick="removeTextBox(this.form)" />--%>
    <%--</p>--%>
    <%--<p><input type="Submit" value="Submit" /></p>--%>
    <%--</form>--%>

    <form action="${pageContext.request.contextPath}/ProcessSchedulingServlet" method="post">
        <h1 style="background: #00FFFF">欢迎进入操作系统进程调度算法系统，请您先输入进程数:</h1>
        进程个数:<input type="text" name="processNum">
        <input type="submit" value="提交">
    </form>
</center>
</body>
</html>
