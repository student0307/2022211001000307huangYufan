<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/3/25
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>

<%@ page import="com.HuangYufan.model.users"%>
<html>
<head>
    <title>userList</title>
</head>
<body>
    <div width="98%" align="center">
    <h2>用户信息</h2>
    </div>
    <table width="98%" border="0" align="center" cellpadding="0"
           cellspacing="1" >
        <tr>
            <th>id</th>
            <th>userName</th>
            <th>password</th>
            <th>email</th>
            <th>gender</th>
            <th>birthday</th>
        </tr>
        <%
            List<users> list = (List<users>) request.getAttribute("users");
            if(list==null || list.size()<1){
                out.println("<tr><td bgcolor='#FFFFFF' colspan='5'>没有任何用户信息！</td></tr>");
            }else {
                for (users user2:list){
        %>
        <tr align="center">
            <td><%=user2.getId() %></td>
            <td><%=user2.getUsername() %></td>
            <td><%=user2.getPassword() %></td>
            <td><%=user2.getEmail() %></td>
            <td><%=user2.getGender() %></td>
            <td><%=user2.getBirthday() %></td>
        </tr>
        <%
                }
            }
        %>


    </table>
</body>
</html>
<%@include file="footer.jsp"%>