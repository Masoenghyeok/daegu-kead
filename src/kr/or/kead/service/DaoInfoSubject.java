package kr.or.kead.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.domain.InfoSubject;
import kr.or.kead.utils.MysqlCon;

public class DaoInfoSubject implements DaoTable{

	@Override
	public int insertDao(Object obj) {
		Connection con = MysqlCon.getConnection();		
		InfoSubject subject = (InfoSubject) obj;
		String sql = "insert into depart values (?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, subject.getCode());
			pstmt.setString(2, subject.getName());
			pstmt.setInt(3, subject.getProf());
			pstmt.setString(4, subject.getTel());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			return -1;
			e.printStackTrace();
		} finally {
			
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDao(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDao(int regNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<InfoStudent> selectDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InfoStudent selectStudentById(int stdId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
