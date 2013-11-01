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
	private String[] tableColumnNames;
	private Class[] tableColumnClasses;
	private Object[] data;
	private ArrayList<Object> arData;
	private String sql;
	
	@Override
	public int getColumnCount() {
		return tableColumnNames.length;
	}

	@Override
	public int getRowCount() {
		return arData.size();
	}


	@Override
	public Class<?> getColumnClass(int col) {
		// TODO Auto-generated method stub
		return tableColumnClasses[col];
	}
	
	public void getResultSet() {		
		Connection con = MysqlCon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsMeta = rs.getMetaData();
			int columnCount = rsMeta.getColumnCount();
			tableColumnNames = new String[columnCount];
			tableColumnClasses = new Class[columnCount];			
			for(int i=1; i<=columnCount; i ++) {
				tableColumnNames[i-1] = rsMeta.getColumnLabel(i);
				tableColumnClasses[i-1] = Class.forName(rsMeta.getColumnName(i));				
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
