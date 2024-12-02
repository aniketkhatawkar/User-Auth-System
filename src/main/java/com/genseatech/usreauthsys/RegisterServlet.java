package com.genseatech.usreauthsys;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        String com_password = request.getParameter("com_password");
        
        try {
            
         // Check user is already register
            if (checkUser.isUserExists(email)) {
            	request.setAttribute("errorMessage", "Email Allready Register, Try With Another or Login");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Validate the password
            if (!(password.length() > 8 && 
            	      password.matches(".*[A-Z].*") &&
            	      password.matches(".*[a-z].*") &&
            	      password.matches(".*\\d.*") &&
            	      password.matches(".*[!@#$%^&*(),.?\":{}|<>].*"))) {
            	    request.setAttribute("passnotgoodMessage", "Password must be over 8 characters, with at least one uppercase, one lowercase, one digit, and one special character.");
            	    RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            	    dispatcher.forward(request, response);
            	    return;
            }
            
         // Check both Pass is same
            if (!password.equals(com_password)) {
                request.setAttribute("errorMessage", "Passwords do not match.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Insert new user into the database
            try (Connection conn = DatabaseConnector.initializeDatabase()) {
                String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.executeUpdate();

                request.setAttribute("successMessage", "Registration Successful, Please Login");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
                
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
	}
}
