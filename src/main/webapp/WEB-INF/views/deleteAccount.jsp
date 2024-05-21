<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/4/12
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Account</title>
    <style>
        /* CSS 样式，使两个表单按钮并排显示 */
        .form-inline {
            display: inline-block;
            margin-right: 10px; /* 可选，为表单添加一些间隔 */
        }
    </style>
</head>
<body>
<p>Are you sure to delete your account?</p>
<form class="form-inline"  method="post" action="deleteAccount">
    <button type="submit">Yes</button>
</form>
<form class="form-inline" method="post" action="javascript:history.back()">
    <button type="submit">No</button>
</form>

<!--<a href="javascript:history.back()">返回上一页</a> -->

</body>
</html>
<%@include file="footer.jsp"%>