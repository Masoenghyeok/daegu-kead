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
		String sql = "insert into professor values(?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prof.getCode());
			pstmt.setString(2, prof.getName());
			pstmt.setInt(3, prof.getDepart());
			pstmt.setString(4, prof.getCourse());
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
		String sql = "update professor set name = ?, depart = ?, course = ? where code = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setString(1, prof.getName());
			pstmt.setInt(2, prof.getDepart());
			pstmt.setString(3, prof.getCourse());
			pstmt.setInt(4, prof.getCode());
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
		String sql = "select code, name, depart, course from professor";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Professor profs;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				profs = new Professor(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
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
		String sql = "select code, name, depart, course from professor where code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Professor profs = null;
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				profs = new Professor(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
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
