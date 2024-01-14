<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <link href="css/registration.scss" rel="stylesheet">
    <title>Registration</title>
</head>
<%String saved_name = (String) request.getAttribute("saved_name");%>
<%String saved_email = (String) request.getAttribute("saved_email");%>

<%String errorName = (String) request.getAttribute("errorName");%>
<%String errorEmail = (String) request.getAttribute("errorEmail");%>
<%String errorPassword = (String) request.getAttribute("errorPassword");%>
<body>
<form method="post" action="registration" class="servletForm"
      id="register-form">
    <div class="form-group">
        <input
            type="text" name="name" id="name" placeholder="Your Name" value="<%= (saved_name != null) ? saved_name : "" %>"/>
    </div>
    <div class="error"><%= errorName %></div>
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
                class="form-submit" value="Register">REGISTRATION</button>
    </div>
</form>
<form method="post" action="login" class="toServletForm">
    <button type="submit" class="toLoginBtn">I am already
        member</button>
</form>
</body>
</html>