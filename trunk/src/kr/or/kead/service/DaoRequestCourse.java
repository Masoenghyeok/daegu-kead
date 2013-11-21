package kr.or.kead.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.kead.domain.Professor;
import kr.or.kead.domain.RequestCourse;
import kr.or.kead.utils.MysqlCon;



public class DaoRequestCourse implements DaoTable {

	@Override
	public int insertDao(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDao(Object obj) {
		Connection con = MysqlCon.getConnection();
		String sql ="update request_course set std_idx =?, course_code=?, grade=? where idx =?";
		RequestCourse reqCourse = (RequestCourse)obj;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reqCourse.getStd_idx());
			pstmt.setInt(2, reqCourse.getCourseCode());
			pstmt.setInt(3, reqCourse.getGrade());
			pstmt.setInt(4, reqCourse.getIdx());			
			System.out.println(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;
		} finally {
			try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}		
		return 0;
	}

	@Override
	public int deleteDao(int regNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Object> selectDao() {
		
		return null;
	}

	@Override
	public Object selectTableById(int idx) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select idx, std_idx, course_code, grade from request_course where course_code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RequestCourse reqCourse = null;
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reqCourse = new RequestCourse(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
			}
			return reqCourse;
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		} finally {
			try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}		
	}

	@Override
	public ArrayList<String> selectTableAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectPasswdByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectTableByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePasswdByEmail(String email, String passwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> selectTableAllListByCode(int code) {
		Connection con = MysqlCon.getConnection();
		ArrayList<String> arLists = new ArrayList<>();
		String sql = "select 과목번호, 과목명 from view_request_infostd_course"
				+ " where 학생번호 = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			System.out.println("selectTable" + pstmt);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				arLists.add(rs.getInt(1) + ":" + rs.getString(2));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}
		return arLists;		
	}

}
