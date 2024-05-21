<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.HuangYufan.model.Product" %><%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/5/21
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp"%>
<%
    //接收Servlet传过来的product
    Product product= (Product) request.getAttribute("product");

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="<%=basePath %>admin/productEdit?productId=<%=product.getProductId()%>">
    <input type="text" name="test" value="<%=product.getProductName()%>">

    <input type="submit">
</form>
</body>
</html>
