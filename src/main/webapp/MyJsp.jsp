<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/3/10
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action="MyDearJsp.jsp">
    Name:<input type="text" name="name"><br/>
    ID:<input type="text" name="id"><br/>
    Subject:<input type="checkbox" name="subject" value="C">C
    <input type="checkbox" name="subject" value="C++">C++
    <input type="checkbox" name="subject" value="Java">Java<br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
