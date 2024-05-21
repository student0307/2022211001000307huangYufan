<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/5/19
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Dear Jsp</title>
</head>
<body>
<h1>Get request parameter using EL</h1>
Name:${param.name}<br>
ID:${param.id}<br>
Subject:${paramValues.subject[0]},${paramValues.subject[1]},${paramValues.subject[2]}
<h1>Get request header using EL</h1>
Host:${header.host}
<h1>Get cookies using EL</h1>
JSESSIONID:${cookie.JSESSIONID.value}
<h1>Get context init parameters EL</h1>
driver:${initParam.driver}<br>
url:${initParam.url}<br>
username:${initParam.username}<br>
password:${initParam.password}<br>

<h1>Get 4 scope variable- page , request, session, application using EL code</h1>
<%
    pageContext.setAttribute("attName","i am in page");
    request.setAttribute("attName","i am in request");
    session.setAttribute("attName","i am in session");
    application.setAttribute("attName","i am in application");
%>
Att Value in page:${pageScope.attName}<br>
Att Value in request:${requestScope.attName}<br>
Att Value in session:${sessionScope.attName}<br>
Att Value in application:${applicationScope.attName}<br>

</body>
</html>
