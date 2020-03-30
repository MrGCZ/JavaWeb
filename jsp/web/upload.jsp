<%--
  Created by IntelliJ IDEA.
  User: GG_WorkStation
  Date: 2020/3/20
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="http://localhost:8080/jsp/uploadServlet" method="post" enctype="multipart/form-data">
        用户名：<input type="text" name="username"/><br>
        头像:<input type="file" name="photo"><br>
        <input type="submit" value="upload">

    </form>


</body>
</html>
