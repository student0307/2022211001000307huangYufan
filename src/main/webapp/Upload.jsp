<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/5/9
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>File Upload to Database Demo</h1>
    <form method="post" action="uploadServlet" enctype="multipart/form-data">
        <table border="0">
            <tr>
                <td>First Name: </td>
                <td><input type="text" name="firstName" size="50"/></td>
            </tr>
            <tr>
                <td>Last Name: </td>
                <td><input type="text" name="lastName" size="50"/></td>
            </tr>
            <tr>
                <td>Portrait Photo: </td>
                <td><input type="file" name="photo" size="50"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
