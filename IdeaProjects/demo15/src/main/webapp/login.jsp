<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <link href="css/registration.scss" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <title>Login</title>
</head>
<body>
<%String saved_email = (String) request.getAttribute("saved_email");%>
<%String errorEmail = (String) request.getAttribute("errorEmail");%>
<%String errorPassword = (String) request.getAttribute("errorPassword");%>
<form method="post" action="login" class="servletForm"
      id="register-form">
    <div class="form-group">
        <input
                type="text" name="email" id="email" placeholder="Your Email" value="<%= (saved_email != null) ? saved_email : "" %>"/>
    </div>
    <div class="error"><%= errorEmail %></div>
    <div class="form-group">
        <input
                type="text" name="password" id="pass" placeholder="Password" />
    </div>
    <div class="error"><%= errorPassword %></div>
    <div class="form-button">
        <button type="submit" name="signup" id="signup"
                class="form-submit" value="Register">LOG IN</button>
    </div>
</form>
<form method="post" action="registration" class="toServletForm">
    <button type="submit" class="toLoginBtn onlyLoginBtn">Create account</button>
</form>
</body>
</html>
