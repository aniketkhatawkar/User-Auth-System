package com.genseatech.usreauthsys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class checkUser{
	
	// Check is user exists or not
	public static boolean isUserExists(String email) throws ClassNotFoundException, SQLException {
		
		Connection conn = DatabaseConnector.initializeDatabase();
		
		String sql = "SELECT * FROM users WHERE email = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, email);
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return true;
		}
		
		return false;	
	}
}
