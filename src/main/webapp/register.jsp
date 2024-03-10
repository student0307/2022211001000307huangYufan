<%--
  Created by IntelliJ IDEA.
  User: 黄雨帆
  Date: 2024/3/10
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script language="JavaScript">
        document.getElementById('myForm').onsubmit = function(event) {
            var isValid = true;
            var errorMessage = "";

            var username = document.getElementById('username').value;
            if (username.length === 0) {
                errorMessage += "Username is required.\n";
                isValid = false;
            }

            var password = document.getElementById('password').value;
            if (password.length < 8) {
                errorMessage += "Password must be at least 8 characters long.\n";
                isValid = false;
            }

            var email = document.getElementById('email').value;
            if (email.length === 0 || !/\S+@\S+\.\S+/.test(email)) {
                errorMessage += "A valid email is required.\n";
                isValid = false;
            }

            var birthdate = document.getElementById('birthdate').value;
            if (!/^\d{4}-\d{2}-\d{2}$/.test(birthdate)) {
                errorMessage += "Birthdate must be in yyyy-mm-dd format.\n";
                isValid = false;
            }

            if (!isValid) {
                event.preventDefault(); // Prevent form submission
                document.getElementById('errorMessages').innerText = errorMessage;
            }

            return isValid;
        }
    </script>
</head>
<body>

<form id="myForm">
    Username: <input type="text" id="username" required><br>
    Password: <input type="password" id="password" required minlength="8"><br>
    Email: <input type="email" id="email" required><br>
    Gender:<input type="radio" name="sex" />Male <input type="radio" name="sex" />Female<br/>
    Birthdate (yyyy-mm-dd): <input type="text" id="birthdate" required><br>
    <button type="submit">Submit</button>
</form>
    <div id="errorMessages"></div>

</body>
</html>
