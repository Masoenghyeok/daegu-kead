package kr.or.kead.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.domain.Depart;
import kr.or.kead.utils.MysqlCon;

public class DaoDepart implements DaoTable{

	@Override
	public int insertDao(Object obj) {
		Connection con = MysqlCon.getConnection();		
		Depart depart = (Depart) obj;
		String sql = "insert into depart values (?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, depart.getCode());
			pstmt.setString(2, depart.getName());
			pstmt.setInt(3, depart.getProf());
			pstmt.setString(4, depart.getTel());
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
		Depart depart = (Depart) obj;
		String sql = "update depart set code=?, name=?, prof=?, tel=? where code = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, depart.getCode());
			pstmt.setString(2, depart.getName());
			pstmt.setInt(3, depart.getProf());
			pstmt.setString(4, depart.getTel());
			pstmt.setInt(5, depart.getCode());			
			pstmt.executeUpdate();	
			System.out.println(pstmt);
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
	public int deleteDao(int regNo) {
		Connection con = MysqlCon.getConnection();		
		String sql = "delete from depart where code = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, regNo);
			System.out.println(pstmt);
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
	public ArrayList<Object> selectDao() {
		Connection con = MysqlCon.getConnection();		
		String sql = "select code, name, prof, tel from depart";
		ArrayList<Object> departs = new ArrayList<>();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {				
				departs.add(new Depart(rs.getInt("code"),rs.getString("name"),
						rs.getInt("prof"),rs.getString("tel")));
			}
			return departs;
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		} finally {
			try { pstmt.close(); con.close();} 
			catch (SQLException e) { e.printStackTrace();}			
		}
		
	}

	@Override
	public Object selectTableById(int code) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select code, name, prof, tel from depart where code = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {				
				return new Depart(rs.getInt("code"),rs.getString("name"),
						rs.getInt("prof"),rs.getString("tel"));
			}else {
				JOptionPane.showMessageDialog(null, "학과 코드를 찾을 수 없습니다.");				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			return null;
		} finally {
			try { pstmt.close(); con.close();} 
			catch (SQLException e) { e.printStackTrace();}			
		}
		return null;
	}
	
	public Object selectCodeByName(String name) {
		Connection con = MysqlCon.getConnection();
		String sql = "select code, name, prof, tel from depart where name = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Depart(rs.getInt(1),rs.getString(2),
						rs.getInt(3),rs.getString(4));
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
	
	public ArrayList<Object> selectNames() {
		Connection con = MysqlCon.getConnection();
		String sql = "select name from depart";
		ArrayList<Object> names =new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				names.add(rs.getString(1));
			}
		}catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}finally {
			try { rs.close();pstmt.close();con.close();} catch (SQLException e) { }
		}		
		return names;
	}
	
	public int selectMaxCode() {
		Connection con = MysqlCon.getConnection();		
		String sql = "select max(code) from professor";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	@Override
	public ArrayList<String> selectTableAllList() {
		Connection con = MysqlCon.getConnection();
		ArrayList<String> arLists = new ArrayList<>();
		String sql = "select code, name from depart";
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
}
