package kr.or.kead.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlCon {
	private static Connection getDatabaseConnection() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/daegu_kead", "kead", "kead_pass");  // 개발원
//			return DriverManager.getConnection("jdbc:mysql://localhost:3306/daegu_kead", "shma", "shma_pass");  // 집
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}	
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = MysqlCon.getDatabaseConnection();
		} catch (ClassNotFoundException e1) {			
			e1.printStackTrace();
		}
		return con;
	}
}
