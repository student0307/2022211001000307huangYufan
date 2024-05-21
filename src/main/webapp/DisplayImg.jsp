<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/5/9
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2><c:out value="${book.title}" /></h2>
    <h3><c:out value="${book.author}" /></h3>
    <img src="data:image/jpg;base64,${book.base64Image}" width="240" height="300"/>
</div>
</body>
</html>
