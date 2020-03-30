<%--
  Created by IntelliJ IDEA.
  User: GG_WorkStation
  Date: 2020/3/20
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.gcz.Student"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        List<Student> stuList = (List<Student>) request.getAttribute("stuList");
    %>
    <table>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
        </tr>

        <%for (Student student : stuList){%>
        <tr>
            <td><%=student.getId()%></td>
            <td><%=student.getName()%></td>
            <td><%=student.getAge()%></td>
        </tr>
        <%}%>
    </table>

</body>
</html>
