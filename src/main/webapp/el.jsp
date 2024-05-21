<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/5/3
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DEMO 1 week -10</title>
</head>
<body>
<%
    pageContext.setAttribute("attName","att in page");
    request.setAttribute("attName","att in request");
    session.setAttribute("attName","att in session");
    application.setAttribute("attName","att in application");
%>
<h2>get 4 scope variable -using java code</h2>
att value:<%=pageContext.getAttribute("attName")%><br>
att value:<%=request.getAttribute("attName")%><br>
att value:<%=session.getAttribute("attName")%><br>
att value:<%=application.getAttribute("attName")%><br>
Find att:<%=pageContext.findAttribute("attName")%><br>
<h2>get 4 scope variable -using EL code</h2>

Att value:${attName}
<h2>not find - get Attribute from session</h2>
Att value:${sessionScope.attName}<br>
Att value:${requestScope.attName}<br>
Att value:${applicationScope.attName}<br>
Att value:${pageScope.attName}<br>

</body>
</html>
