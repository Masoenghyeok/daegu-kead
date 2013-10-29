package kr.or.kead.ui.departmgn;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import kr.or.kead.domain.Depart;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoTable;

public class CustomDepartTableModel extends AbstractTableModel {
	private ArrayList<Object> departs;
		
	// 테이블 column의 이름
	private static final String[] columnNames = {"번호", "학과명","지도교수","연락처"};
	
	// column 에 해당하는 자료형
	private static final Class[] columnClasses = {
		Integer.class, String.class, Integer.class, String.class
	};
	
	public CustomDepartTableModel() {
		DaoTable dao = new DaoDepart();
		departs = dao.selectDao();
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return columnNames[col];
	}

	@Override
	public Class getColumnClass(int col) {
		return columnClasses[col];
	}
	
	@Override
	public int getRowCount() {		
		return departs.size();
	}


	@Override
	public Object getValueAt(int row, int col) {
		Depart depart = (Depart)departs.get(row);		
			
		switch(col) {
		case 0 : return depart.getCode();
		case 1 : return depart.getName();
		case 2 : return depart.getProf();
		case 3 : return depart.getTel();		
		}
		return "";
	}

}
