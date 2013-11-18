package kr.or.kead.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.kead.domain.Handicap;
import kr.or.kead.utils.MysqlCon;

public class DaoHandicap implements DaoTable {

	@Override
	public int insertDao(Object obj) {
		Connection con = MysqlCon.getConnection();
		Handicap handi = (Handicap)obj;
		String sql = "insert into handicap values(?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, handi.getCode());
			pstmt.setString(2, handi.getName());
			pstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;
		} finally {
			try { pstmt.close();con.close();} catch (SQLException e) { }
		}
		return 0;
	}

	@Override
	public int updateDao(Object obj) {
		Connection con = MysqlCon.getConnection();
		Handicap handi = (Handicap)obj;
		String sql = "update handicap set name=? where code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, handi.getName());
			pstmt.setInt(2, handi.getCode());
			pstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;
		}finally {
			try { pstmt.close();con.close();} catch (SQLException e) { }
		}
		return 0;
	}

	@Override
	public int deleteDao(int regNo) {
		Connection con = MysqlCon.getConnection();
		String sql = "delete from handicap where code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {pstmt.close();con.close();} catch (SQLException e) { }
		}
		return 0;
	}

	@Override
	public ArrayList<Object> selectDao() {
		Connection con = MysqlCon.getConnection();
		ArrayList<Object> handicaps = new ArrayList<>();
		String sql = "select name from handicap";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				handicaps.add(rs.getString(1));
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}finally {
			try {rs.close(); pstmt.close();con.close();} catch (SQLException e) { }
		}
		return handicaps;
	}

	@Override
	public Object selectTableById(int idx) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select name from handicap where code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}finally {
			try { rs.close();pstmt.close();con.close();} catch (SQLException e) { }
		}		
		return null;
	}
	
	public int selectCodeHandiByName(String name) {
		Connection con = MysqlCon.getConnection();
		String sql = "select code from handicap where name=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res = -1;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if( rs.next()) {
				res =  rs.getInt(1);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;
		}finally {
			try { rs.close();pstmt.close();con.close();} catch (SQLException e) { }
		}		
		return res;
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
}
