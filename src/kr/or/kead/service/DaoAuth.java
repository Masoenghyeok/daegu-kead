package kr.or.kead.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.kead.domain.Auth;
import kr.or.kead.utils.MysqlCon;


public class DaoAuth  {
	public String selectTableByEmail (String email) {
		Connection con = MysqlCon.getConnection();
		String sql = "select email , level from auth where email =?";	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1)+":"+rs.getInt(2);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		} finally {
			try {rs.close();pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}		
		return null;
	}
	
	public String selectPasswdByEmail(String email) {
		Connection con = MysqlCon.getConnection();
		String sql = "select passwd from auth where email =?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		} finally {
			try {rs.close();pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}		
		return null;
	}
	
}
