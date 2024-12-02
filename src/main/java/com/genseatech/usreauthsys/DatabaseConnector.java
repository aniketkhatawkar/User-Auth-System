package com.genseatech.usreauthsys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector{
	
	// Provide connectivity
	public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:mysql://localhost:3306/user_auth_sys";
        String dbUsername = "root";
        String dbPassword = "12345678";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }
}
