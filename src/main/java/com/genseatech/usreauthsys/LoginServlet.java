package com.genseatech.usreauthsys;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Connection conn = DatabaseConnector.initializeDatabase();
			
			
			String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();
			
			// Login into system
			if (resultSet.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("user", email);
				response.sendRedirect("home.jsp");
			} else {
				request.setAttribute("errorMessage", "Invalid email or password");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
			// Close connections
			resultSet.close();
			statement.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Login failed", e);
		}
	}
}
