<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/4/13
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find By Username</title>
</head>
<body>
<%
    if (request.getAttribute("ErrorMessage")!=null)
        out.print("<h3>"+request.getAttribute("ErrorMessage")+"</h3>");
%>

    <form method="post" action="FindByUsername">
         Username:<input type="text" name="username">
        <button type="submit">Search</button>

    </form>
</body>
</html>

<%@include file="footer.jsp"%>