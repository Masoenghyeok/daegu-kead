package kr.or.kead.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.domain.StdAddress;
import kr.or.kead.utils.MysqlCon;

public class DaoAddr implements DaoTable{
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
	public ArrayList<StdAddress> selectStdAddrByDong(String dong) {
		Connection con = getConnection();
		StdAddress addr;
		ArrayList<StdAddress> stdAddr = new ArrayList<>();
		String sql = "select * from post where dong like ?, bunji like ?";		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			System.out.println(dong);
			dong = "%" + dong + "%";
			pstmt.setString(1, dong);			
			System.out.println("pstmt = " + pstmt);
			ResultSet rs = pstmt.executeQuery();	
			while(rs.next()) {
				addr = new StdAddress();
				addr.setSeq(rs.getInt("seq"));
				addr.setZip_code(rs.getString("zipcode"));
				addr.setSido(rs.getString("sido"));
				addr.setGugun(rs.getString("gugun"));
				addr.setDong(rs.getString("dong"));
				addr.setBunji(rs.getString("bunji"));
				System.out.println(addr.getSeq());
				stdAddr.add(addr);				
			}			
		} catch (SQLException e) {			
			e.printStackTrace();	
			return null;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		return stdAddr;
	}



	@Override
	public int insertDao(Object obj) {
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
