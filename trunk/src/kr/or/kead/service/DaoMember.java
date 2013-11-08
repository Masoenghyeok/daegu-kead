package kr.or.kead.service;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.kead.domain.MemberInfo;
import kr.or.kead.utils.MysqlCon;


public class DaoMember  {
	public Object compareIdPass(String id, String pass) {
		Connection con = MysqlCon.getConnection();
		String sql = "select idx, id, pass, level from member where id =? and pass = ?";
		MemberInfo mem;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mem = new MemberInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				return mem;
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
