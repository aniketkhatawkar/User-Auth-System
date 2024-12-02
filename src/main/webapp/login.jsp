<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
        <input type="text" name="email" placeholder="Enter Email" required>
        <input type="password" name="password" placeholder="Enter Password" required>
        <button type="submit">Login</button>
    </form>
    
    <div class="error-message">
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <%= errorMessage != null ? errorMessage : "" %>
    </div>
    
    <div class="success-message">
        <% String successmessage = (String) request.getAttribute("successMessage"); %>
        <%= successmessage != null ? successmessage : "" %>
    </div>
   
    <p>Don’t have an account? <a href="register.jsp">Register here</a></p>
</div>
</body>
</html>
