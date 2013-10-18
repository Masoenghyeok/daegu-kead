package kr.or.kead.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.utils.MysqlCon;

public class DaoInfoStudent implements DaoTable {
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = MysqlCon.getDatabaseConnection();
		} catch (ClassNotFoundException e1) {			
			e1.printStackTrace();
		}
		return con;
	}
	
	@Override
	public int insertDao(Object obj) {
		Connection con = getConnection();		
		InfoStudent std = (InfoStudent) obj;
		String sql = "insert into infoStudent values(null,?,?,?,?,?,?,?,?,?,?,?)";		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, std.getStdName());
			pstmt.setString(2, std.getJuminNum());
			pstmt.setDate(3, new Date(std.getStartDate().getTime()));
			pstmt.setDate(4, new Date(std.getEndDate().getTime()));
			pstmt.setString(5, std.getMobile());
			pstmt.setString(6, std.getTel());
			pstmt.setString(7, std.getStdAddr());
			pstmt.setInt(8, std.getRoomNum());
			pstmt.setInt(9, std.getStdType());
			pstmt.setInt(10, std.getGrade());
			pstmt.setString(11, std.getEmail());
			
			pstmt.execute();			
		} catch (SQLException e) {			
			return -1;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public int updateDao(Object obj) {
		Connection con = getConnection();
		InfoStudent std = (InfoStudent) obj;
		String sql = "update infoStudent set"
				+ " stdName=?,"
				+ " juminNum=?,"
				+ " startDate=?,"
				+ " endDate=?,"
				+ " mobile=?,"
				+ " tel=?,"
				+ " stdAddr=?,"
				+ " roomNum=?,"
				+ " stdType=?,"
				+ " grade=?,"
				+ " email=? where idx=?";		
		System.out.println(sql);
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, std.getStdName());			
			pstmt.setString(2, std.getJuminNum());
			pstmt.setDate(3, new Date(std.getStartDate().getTime()));
			pstmt.setDate(4, new Date(std.getEndDate().getTime()));
			pstmt.setString(5, std.getMobile());
			pstmt.setString(6, std.getTel());
			pstmt.setString(7, std.getStdAddr());
			pstmt.setInt(8, std.getRoomNum());
			pstmt.setInt(9, std.getStdType());
			pstmt.setInt(10, std.getGrade());
			pstmt.setString(11, std.getEmail());
			pstmt.setInt(12, std.getIdx());			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {		
			e.printStackTrace();
			return -1;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int deleteDao(int regNo) {
		Connection con = getConnection();
		String sql = "delete from infoStudent where idx = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<InfoStudent> selectDao() {
		Connection con = getConnection();
		String sql = "select * from infoStudent";
		InfoStudent std;
		ArrayList<InfoStudent> sendValue = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				std = new InfoStudent();
				std.setIdx(rs.getInt("idx"));
				std.setStdName(rs.getString("stdName"));
				std.setJuminNum(rs.getString("juminNum"));
				std.setStartDate(rs.getDate("startDate"));
				std.setEndDate(rs.getDate("endDate"));
				std.setMobile(rs.getString("mobile"));
				std.setTel(rs.getString("tel"));
				std.setStdAddr(rs.getString("stdAddr"));
				std.setRoomNum(rs.getInt("roomNum"));
				std.setStdType(rs.getInt("stdType"));
				std.setGrade(rs.getInt("grade"));
				std.setEmail(rs.getString("email"));				
				sendValue.add(std);
			}			
			return sendValue;		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public InfoStudent selectStudentById(int stdId) {
		Connection con = getConnection();		
		String sql = "select * from infoStudent where idx= ?";
		InfoStudent std = new InfoStudent();		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stdId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				std.setIdx(rs.getInt("idx"));
				std.setStdName(rs.getString("stdName"));
				std.setJuminNum(rs.getString("juminNum"));
				std.setStartDate(rs.getDate("startDate"));
				std.setEndDate(rs.getDate("endDate"));
				std.setMobile(rs.getString("mobile"));
				std.setTel(rs.getString("tel"));
				std.setStdAddr(rs.getString("stdAddr"));
				std.setRoomNum(Integer.parseInt(rs.getString("roomNum")));
				std.setStdType(Integer.parseInt(rs.getString("stdType")));
				std.setGrade(Integer.parseInt(rs.getString("grade")));
				std.setEmail(rs.getString("email"));
			}else {
				JOptionPane.showMessageDialog(null, "검색하신 인덱스는 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}		
		return std;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
}
