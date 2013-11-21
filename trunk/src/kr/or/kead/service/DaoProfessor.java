package kr.or.kead.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.kead.domain.Professor;
import kr.or.kead.utils.MysqlCon;

public class DaoProfessor implements DaoTable {

	@Override
	public int insertDao(Object obj) {
		Connection con = MysqlCon.getConnection();
		Professor prof = (Professor)obj;
		String sql = "insert into professor (code, name, depart, course, email) "
				+ "values(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prof.getCode());			
			pstmt.setString(2, prof.getName());
			pstmt.setInt(3, prof.getDepart());
			pstmt.setString(4, prof.getCourse());			
			pstmt.setString(5, prof.getEmail());
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
	public int updateDao(Object obj) {
		Connection con = MysqlCon.getConnection();
		Professor prof = (Professor)obj;
		String sql = "update professor set email = ?, passwd = ?, "
				+ "name = ?, depart = ?, course = ? where code = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prof.getEmail());
			pstmt.setString(2, prof.getPass());
			pstmt.setString(3, prof.getName());
			pstmt.setInt(4, prof.getDepart());
			pstmt.setString(5, prof.getCourse());
			pstmt.setInt(6, prof.getCode());
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
		Connection con = MysqlCon.getConnection();		
		String sql = "delete from professor where code = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regNo);
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
		Connection con = MysqlCon.getConnection();
		ArrayList<Object> arProf = new ArrayList<>();
		String sql = "select code, email, passwd, name, depart, course from professor";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Professor profs;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				profs = new Professor(rs.getInt(1), rs.getString(2), rs.getString(3), 
						 rs.getString(4), rs.getInt(5), rs.getString(6));
				arProf.add(profs);
			}
			return arProf;
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		} finally {
			try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}		
	}

	@Override
	public Object selectTableById(int idx) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select code, email, passwd, name, depart, course from professor where code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Professor profs = null;
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				profs = new Professor(rs.getInt(1), rs.getString(2), rs.getString(3), 
						 rs.getString(4), rs.getInt(5), rs.getString(6));
			}
			return profs;
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
		String sql = "select code, name from professor";
		ArrayList<String> arProf = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try {
			pstmt = con.prepareStatement(sql);		
			rs = pstmt.executeQuery();
			while(rs.next()) {
				arProf.add(rs.getInt(1) + ":" + rs.getString(2));
			}
			return arProf;
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		} finally {
			try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}	
	}
	
	public Object selectCodeNameById(int code) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select code, name from professor where code = ?";
		Object obj = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				obj = rs.getInt(1) + ":" + rs.getString(2);
			}
			return obj;
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		} finally {
			try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}	
	}
	
	public int selectMaxCode(int code) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select max(code) from professor where depart = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
	
	public ArrayList<String> selectDepartCodeByName(int code) {
		Connection con = MysqlCon.getConnection();
		ArrayList<String> arLists = new ArrayList<>();
		String sql = "select code, name from professor where depart = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			while(rs.next()) {
				arLists.add(rs.getInt(1) + ":" + rs.getString(2));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}
		return arLists;
	}
	
	public ArrayList<String> selectDepartCodeByCourse(int departCode) {
		Connection con = MysqlCon.getConnection();
		ArrayList<String> arCourses = new ArrayList<>();
		String sql = "select course from professor where depart = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, departCode);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			while(rs.next()) {
				arCourses.add(rs.getInt(1)+"");				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}
		return arCourses;
	}

	@Override
	public String selectPasswdByEmail(String email) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select passwd from professor where email = ?";		
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
			try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return null;
	}

	@Override
	public Professor selectTableByEmail(String email) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select code, email, passwd, name, depart, course from professor where email = ?";
		Professor prof = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				prof = new Professor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getString(6));
				return prof;
			}		
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		} finally {
			try {pstmt.close();con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return null;
	}

	@Override
	public int updatePasswdByEmail(String passwd, String email) {
		Connection con = MysqlCon.getConnection();		
		String sql = "update professor set passwd = ? where email = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setString(2, email);
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
	public ArrayList<String> selectTableAllListByCode(int code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
