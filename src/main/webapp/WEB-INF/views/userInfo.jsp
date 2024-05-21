<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/4/7
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
<h1>User Info</h1>
<%!
users user;
%>
<%
    HttpSession session1=request.getSession(false);
    user= (users) session1.getAttribute("user");
%>

<table>
    <tr><td>Id:</td><td><%=user.getId()%></td></tr>
    <tr><td>Username:</td><td><%=user.getUsername()%></td></tr>
    <tr><td>Password:</td><td><%=user.getPassword()%></td></tr>
    <tr><td>Email:</td><td><%=user.getEmail()%></td></tr>
    <tr><td>Gender:</td><td><%=user.getGender()%></td></tr>
    <tr><td>Birth:</td><td><%=user.getBirthday()%></td></tr>
</table>
<%
    if((request.getAttribute("failed")!=null)){
        out.print("<h3>"+request.getAttribute("failed")+"</h3>");
    }
%>
<br><br><br><a href="update">Update Account </a>
<br><a href="deleteAccount">Delete Account</a><br/>
</body>
</html>
<%@include file="footer.jsp"%>