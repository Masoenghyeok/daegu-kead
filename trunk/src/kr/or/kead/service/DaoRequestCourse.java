package kr.or.kead.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.kead.domain.Depart;
import kr.or.kead.domain.Professor;
import kr.or.kead.domain.RequestCourse;
import kr.or.kead.utils.MysqlCon;



public class DaoRequestCourse implements DaoTable {

	@Override
	public int insertDao(Object obj) {
		Connection con = MysqlCon.getConnection();		
		RequestCourse reqCourse = (RequestCourse) obj;
		String sql = "insert into request_course (std_idx, course_code) values (?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reqCourse.getStd_idx());
			pstmt.setInt(2, reqCourse.getCourseCode());					
			pstmt.executeUpdate();			
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;
		} finally {
			try { pstmt.close(); con.close();} 
			catch (SQLException e) { e.printStackTrace();}			
		}
		return 0;
	}
	
	@Override
	public int updateDao(Object obj) {
		Connection con = MysqlCon.getConnection();
		ArrayList<Object> arProf = new ArrayList<>();
		String sql = "update request_course set std_idx = ?, course_code = ?, grade = ? where idx = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RequestCourse reqCourse = (RequestCourse)obj;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reqCourse.getStd_idx());
			pstmt.setInt(2, reqCourse.getCourseCode());
			pstmt.setInt(3, reqCourse.getGrade());
			pstmt.setInt(4, reqCourse.getIdx());
			pstmt.executeUpdate();
			return 0;
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;
		} finally {
			try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}		
	}	

	@Override
	public int deleteDao(int regNo) {
		Connection con = MysqlCon.getConnection();		
		String sql = "delete from request_course where code = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regNo);
			System.out.println("reg no = " + regNo);
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
	public ArrayList<Object> selectDao() {
		
		return null;
	}

	@Override
	public Object selectTableById(int idx) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select idx, std_idx, course_code, grade from request_course where idx = ?";
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
		Connection con = MysqlCon.getConnection();
		ArrayList<String> arLists = new ArrayList<>();
		String sql = "select 번호, 학생명, 과목명 from view_request_infostd_course";			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);		
			rs = pstmt.executeQuery();
			while(rs.next()) {
				arLists.add(rs.getString(1) + ":" +rs.getString(2) + ":" + rs.getString(3));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}
		return arLists;	
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
		String sql = "select 번호, 학생명, 과목명 from view_request_infostd_course"
				+ " where 번호 = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				arLists.add(rs.getInt(1) + ":" + rs.getString(2)+ ":" + rs.getString(3));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}
		return arLists;		
	}
	
	public Object selectTableByStd_idx(int std_idx) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select idx, std_idx, course_code, grade from request_course where std_idx = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RequestCourse reqCourse = null;
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, std_idx);
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
	
	public Object selectTableByCourse_code(int course_code) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select idx, std_idx, course_code, grade from request_course where course_code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RequestCourse reqCourse = null;
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, course_code);
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
	

	

}
