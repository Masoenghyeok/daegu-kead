package kr.or.kead.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.kead.domain.Course;
import kr.or.kead.domain.ViewCourse;
import kr.or.kead.utils.MysqlCon;



public class DaoCourse implements DaoTable {

	@Override
	public int insertDao(Object obj) {
		Connection con = MysqlCon.getConnection();
		String sql = "insert into course (code, depart_code, subject, material, prof_code) values "
				+ "(?, ?, ?, ?, ?) ";
		PreparedStatement pstmt = null;
		Course course = (Course)obj;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, course.getCode());
			pstmt.setInt(2, course.getDepart_code());
			pstmt.setString(3, course.getSubject());
			pstmt.setString(4, course.getMaterial());
			pstmt.setInt(5, course.getProf_code());
			pstmt.executeUpdate();			
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;			
		}finally {try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}

	@Override
	public int updateDao(Object obj) {
		Connection con = MysqlCon.getConnection();
		String sql = "update course set code=?, depart_code=?, subject=?, "
				+ "material=?, prof_code=? where code = ?";
		PreparedStatement pstmt = null;
		Course course = (Course)obj;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, course.getCode());
			pstmt.setInt(2, course.getDepart_code());
			pstmt.setString(3, course.getSubject());
			pstmt.setString(4, course.getMaterial());
			pstmt.setInt(5, course.getProf_code());
			pstmt.setInt(6, course.getCode());
			pstmt.executeUpdate();			
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;			
		}finally {try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}

	@Override
	public int deleteDao(int regNo) {
		Connection con = MysqlCon.getConnection();
		String sql = "delete from course where code = ?";
		PreparedStatement pstmt = null;		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regNo);			
			pstmt.executeUpdate();			
		} catch (SQLException e) {e.printStackTrace();return -1;			
		}finally {try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}

	@Override
	public ArrayList<Object> selectDao() {
		Connection con = MysqlCon.getConnection();
		String sql = "select 강좌코드, 학과, 강좌명, 교재명, 교수명 from view_course_depart_prof";
		ArrayList<Object> courses = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			pstmt = con.prepareStatement(sql);					
			rs = pstmt.executeQuery();
			while(rs.next()) {
				courses.add(new ViewCourse(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5)));
			}
			return courses;
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;			
		}finally {try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}		
	}

	@Override
	public Object selectTableById(int stdId) {
		Connection con = MysqlCon.getConnection();
		String sql = "select code, depart_code, subject, material, prof_code from course"
				+ " where code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, stdId);
			System.out.println("selectTableById = " + pstmt);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Course(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), rs.getInt(5));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;			
		}finally {try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return null;
	}

	@Override
	public ArrayList<String> selectTableAllList() {
		Connection con = MysqlCon.getConnection();
		ArrayList<String> arLists = new ArrayList<>();
		String sql = "select code, subject from course";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
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

	@Override
	public String selectPasswdByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectTableByEmail(String email) {

		return null;
	}

	@Override
	public int updatePasswdByEmail(String email, String passwd) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int selectMaxCode(int depart_code) {
		Connection con = MysqlCon.getConnection();
		String sql = "select max(code) from course"
				+ " where depart_code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, depart_code);			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;			
		}finally {try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return -1;
	}
	
	public ArrayList<String> selectTableAllListByCode(int code) {
		Connection con = MysqlCon.getConnection();
		ArrayList<String> arLists = new ArrayList<>();
		String sql = "select code, subject from course where prof_code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
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
