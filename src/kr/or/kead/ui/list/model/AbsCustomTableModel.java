package kr.or.kead.ui.list.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import kr.or.kead.utils.MysqlCon;

public abstract class AbsCustomTableModel extends AbstractTableModel {
	private String[] tableColumnNames;			// 테이블 표시할 컬럼 이름
	private Class[] tableColumnClasses;			// 테이블 자료 타입
	private Object[] data;						// 하나의 레코드
	protected ArrayList<Object[]> arData;		// 레코드의 묶음
	protected String sql;
	
	@Override
	public int getColumnCount() {
		return tableColumnNames.length;
	}

	@Override
	public int getRowCount() {
		return arData.size();
	}	

	@Override
	public String getColumnName(int col) {
		return tableColumnNames[col];
	}

	@Override
	public Class<?> getColumnClass(int col) {
		// TODO Auto-generated method stub
		return tableColumnClasses[col];
	}
	
	
	@Override
	public Object getValueAt(int arg0, int arg1) {
		
		return null;
	}

	

	public void getResultSet() {		
		Connection con = MysqlCon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			arData = new ArrayList<>();
			ResultSetMetaData rsMeta = rs.getMetaData();
			int columnCount = rsMeta.getColumnCount();
			tableColumnNames = new String[columnCount];
			tableColumnClasses = new Class[columnCount];
			
			for(int i=1; i<=columnCount; i ++) {
				tableColumnNames[i-1] = rsMeta.getColumnLabel(i);
				tableColumnClasses[i-1] = Class.forName(rsMeta.getColumnClassName(i));				
			}
			
			while(rs.next()) {
				data = new Object[columnCount];
				for(int i=1;i<=columnCount; i ++) {
					data[i-1] = rs.getObject(i);					
				}
				arData.add(data);				
			}
		} catch (SQLException e) {e.printStackTrace();}catch(ClassNotFoundException e){e.printStackTrace();
		} finally {
			try {rs.close();pstmt.close();con.close();} catch (SQLException e){e.printStackTrace();}
		}
	}
	
	public void refreshData() {
		getResultSet();
		fireTableDataChanged();
	}	

}
