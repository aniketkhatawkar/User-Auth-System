<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h2>Register</h2>
    <form action="RegisterServlet" method="post">
        <input type="text" name="name" placeholder="Enter Your Full Name" required>
        <input type="text" name="email" placeholder="Enter Email" required>
        <input type="password" id="password" name="password" placeholder="Enter Password" required>
        <input type="password" name="com_password" placeholder="Enter Password Again" required>
        <button type="submit">SignUp</button>
    </form>

    <div class="error-message">
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <%= errorMessage != null ? errorMessage : "" %>
    </div>
    
    <div class="pass-message">
        <% String passnotgoodMessage = (String) request.getAttribute("passnotgoodMessage"); %>
        <%= passnotgoodMessage != null ? passnotgoodMessage : "" %>
    </div>

    <p>Already have an account? <a href="login.jsp">Login here</a></p>
</div>
</body>
</html>
