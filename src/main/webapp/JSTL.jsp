<%@ page import="java.util.ArrayList" %>
<%@ page import="com.HuangYufan.model.users" %><%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/5/6
  Time: 8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>print 1 to 10 using c:forEach</h1>
<ul>
    <% for (int i = 0; i < 10; i++) {
    }%>
    <c:forEach var="i" begin="1" end="10" >
        <li>${i}</li>
    </c:forEach>

</ul>
<h1>print 1 to 10 using c:forEach using step</h1>
<ul>
    <% for (int i = 0; i < 10; i++) {
    }%>
    <c:forEach var="i" begin="1" end="10" step="2">
        <li>${i}</li>
    </c:forEach>

</ul>

<hr>
<h1>c:forEach get from array</h1>
<%
String [] words={"foo","bar","baz"};
request.setAttribute("words",words);
%>
<ul>
    <c:forEach var="w" items="${words}">
        <li>${w}</li>
    </c:forEach>
</ul>

<hr>
<h1>c:forEach get from arrayList</h1>
<%
    ArrayList<String> lastName=new ArrayList<>();
    lastName.add("Huang");
    lastName.add("Lee");
    lastName.add("Li");
    request.setAttribute("lastName",lastName);
%>
<ul>
    <c:forEach var="w" items="${lastName}">
        <li>${w}</li>
    </c:forEach>
</ul>

<hr>
<h1>c:forToken use</h1>
<ul>
    <c:forTokens var="w" items="One,Two,THree,four.five.six" delims=",.">
        <li>${w}</li>
    </c:forTokens>
</ul>

<%
String username="mike";
request.setAttribute("username",username);
%>
<hr>
<h1>c:out</h1>
<h2><c:out value="<font color:red> mike" default="guest" escapeXml="false"> </c:out>

<%
users user=new users();
//user.setUsername("admin");
user.setPassword("12345");
request.setAttribute("user",user);
%>

<h1>user c:if</h1>
    <c:forEach var="i" begin="1" end="10">
    <li>${i}</li>
    <c:if test="${i>7}">
    (bigger)
    </c:if>
    </c:forEach>

<ul>
    <h1>user c:when</h1>
    <c:forEach var="i" begin="1" end="10">
        <li>${i}</li>
        <c:if test="${i>7}">
            (bigger)
        </c:if>
        <c:choose>
        <c:when test="${i<4}">
            (small)
        </c:when>
        <c:when test="${i<8}">
            (mid)
        </c:when>
        </c:choose>
    </c:forEach>

</ul>
    <h1>use c:import to add baidu</h1>
    <c:import url="http://www.baidu.com"/>

    <h1>user c:url</h1>
    <c:url var="HomeUrl" value="index.jsp">

    </c:url>
</body>
</html>
