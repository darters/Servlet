<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/index.scss">
    <title>MainPage</title>
</head>
<body>
<h1>
    <%
    String userName = (String) session.getAttribute("name");
    if(userName == null) {
        userName = "You are not logged in to your account";
    }
        %>
    <h1><%= userName %></h1>
    <form method="post" action="login" class="register-form"
          id="register-form">
        <div class="form-button">
            <button type="submit" name="signup" id="signup"
                    class="form-submit" value="Register">LOG IN</button>
        </div>
    </form>
    <form method="post" action="registration" class="toServletForm">
        <button type="submit" class="toLoginBtn onlyLoginBtn">Create account</button>
    </form>
</h1>
</body>
</html>
