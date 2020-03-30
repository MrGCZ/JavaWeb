<%--
  Created by IntelliJ IDEA.
  User: GG_WorkStation
  Date: 2020/3/21
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--base标签，固定相对路径跳转的结构-->
<!--动态获取服务器的ip和工程路径 在web下-->
<%
    String basePath =request.getScheme() +
                    "://" +request.getServerName()+
                    ":"+request.getServerPort()+
                    request.getContextPath()
                    +"/";
    pageContext.setAttribute("basePath",basePath);
%>

<base href=<%=basePath%>>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>