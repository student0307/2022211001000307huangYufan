<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/4/13
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Find BY ID<br>
<%
if (request.getAttribute("ErrorMessage")!=null)
    out.print("<h3>"+request.getAttribute("ErrorMessage")+"</h3>");
%>

<br>
<form method="post" action="FindById">
    ID:<input type="text" name="id">
    <button input type="submit"> Search </button>
</form>
</body>
</html>
<%@include file="footer.jsp"%>