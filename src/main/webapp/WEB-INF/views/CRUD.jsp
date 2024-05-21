<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/4/11
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD</title>
</head>
<body>
<%
    if(request.getAttribute("ErrorMessage")!=null)
    out.print(request.getAttribute("ErrorMessage"));
%>
<br>
User Control<br/>
<a href="FindById">Find By ID</a><br/>
<a href="FindByUsername">Find By Username</a><br/>
<a href="FindAllUser">Find All User</a><br/>
</body>
</html>
<%@include file="footer.jsp"%>