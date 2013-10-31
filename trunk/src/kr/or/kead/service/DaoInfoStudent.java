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
	@Override
	public int insertDao(Object obj) {
		Connection con = MysqlCon.getConnection();		
		InfoStudent std = (InfoStudent) obj;
		String sql = "insert into infoStudent values(null,?,?,?,?,?,?,?,?,?,?,?,?)";	
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
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
			pstmt.setInt(12, std.getDepartCode());
			System.out.println(pstmt);
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public int updateDao(Object obj) {
		Connection con = MysqlCon.getConnection();
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
				+ " email=?,"
				+ " departCode=? where idx=?";		
		PreparedStatement pstmt=null;
		try {
			pstmt = con.prepareStatement(sql);
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
			pstmt.setInt(12, std.getDepartCode());
			pstmt.setInt(13, std.getIdx());			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {		
			e.printStackTrace();
			return -1;
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int deleteDao(int regNo) {
		Connection con = MysqlCon.getConnection();
		String sql = "delete from infoStudent where idx = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			return -1;
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<Object> selectDao() {
		Connection con = MysqlCon.getConnection();
		String sql = "select * from infoStudent";
		InfoStudent std;
		ArrayList<Object> sendValue = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
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
				std.setDepartCode(rs.getInt("departCode"));
				sendValue.add(std);
			}			
			return sendValue;		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public InfoStudent selectTableById(int idx) {
		Connection con = MysqlCon.getConnection();		
		String sql = "select * from infoStudent where idx= ?";
		InfoStudent std = new InfoStudent();	
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
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
				std.setDepartCode(rs.getInt("departCode"));
			}else {
				JOptionPane.showMessageDialog(null, "아이디를 찾을 수 없습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}		
		return std;
	}

	@Override
	public ArrayList<String> selectTableAllList() {
		Connection con = MysqlCon.getConnection();		
		String sql = "select idx, stdName from infoStudent";
		InfoStudent std = new InfoStudent();
		ArrayList<String> lists = new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs = null;		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				lists.add(rs.getInt(1) + ":" + rs.getString(2));
			}			
		}catch (SQLException e) {			
			e.printStackTrace();
			return null;
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return lists;
	}
}
