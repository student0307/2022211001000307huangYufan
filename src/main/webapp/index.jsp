<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h2>Welcome to My Online Shop Home Page.</h2><br>
<a href="WEB-INF/views/admin/index.jsp">TEST</a><br/>

<form method="get" target="_blank" action="search">
<!-- <url-pattern>/search</url-pattern> -->
    <input type="text" name="txt" size="30"/>
    <select name="search">
        <option value="baidu">Baidu</option>
        <option value="bing">Bing</option>
        <option value="google">Google</option>
    </select>
    <input type="submit" value="Search"/>
</form>>

Welcome to my home page. <br>
<br/>
<br/>
<a href="hello-servlet">Hello Servlet-week1</a><br/>
<a href="MyJsp.jsp">Student Info Servlet-week2</a><br/>
<a href="life">Life Cycle Servlet-week3</a><br/>
<a href="register.jsp">Register-getParameter-week3</a><br/>
<a href="config">Config Parameter-week4</a><br/>
<a href="WEB-INF/views/registerJDBC.jsp">Register JDBC-week4</a><br/>
<a href="MyJsp1.jsp">include-week5</a><br/>
<a href="WEB-INF/views/login.jsp">Login-week5</a><br/>

</body>
</html>
<%@include file="footer.jsp"%>