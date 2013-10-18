package kr.or.kead.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlCon {
	public static Connection getDatabaseConnection() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/daegu_kead", "kead", "kead_pass");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
