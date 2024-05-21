<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/4/8
  Time: 8:35
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Update User Info</h1>
<%
    HttpSession session1=request.getSession(false);
    users user = (com.HuangYufan.model.users) session1.getAttribute("loginUser");
%>
<%
    String gender=user.getGender();
    System.out.println(gender);
    System.out.println("male".equals(gender));
    System.out.println("female".equals(gender));

%>
<td bgcolor="#f5deb3">
    <form id="myForm" method="post" action="update">
        <input type="hidden" name="id" value=<%=user.getId()%>><br>
        Username: <input type="text" id="username" name="username" value=<%=user.getUsername()%>><br>
        Password: <input type="password" id="password" name="password" value=<%=user.getPassword()%> ><br>
        Email: <input type="email" id="email" name="email" value=<%=user.getEmail()%> ><br>
        Gender:<input type="radio" name="gender" value="male" <%= ("male".equals(user.getGender().trim())?" checked":"") %>>Male
        <input type="radio" name="gender" value="female" <%= ("female".equals(user.getGender().trim())?" checked":"") %>>Female<br/>
        Birthdate (yyyy-mm-dd): <input type="text" id="birthdate" name="birthday" value=<%=user.getBirthday()%>><br>
        <button type="submit">Save Change</button>
    </form>

</td>

</body>
</html>
<%@include file="footer.jsp"%>
