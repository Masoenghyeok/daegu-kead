package kr.or.kead.ui.stdmgn;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import kr.or.kead.domain.Depart;
import kr.or.kead.domain.InfoStudent;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoHandicap;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoTable;

public class CustomStdTableModel extends AbstractTableModel {
	private ArrayList<Object> students;
	private DaoDepart daoDepart;
	private DaoHandicap daoHandi;
		
	// 테이블 column의 이름
	private static final String[] columnNames = {"번호","이름","주민번호","입학일자","수료일자","휴대폰",
		"집전화","주소","통학/기숙",
		"장애유형","장애등급","Email","분야"};
	
	// column 에 해당하는 자료형
	private static final Class[] columnClasses = {
		Integer.class, String.class, String.class, String.class, String.class, String.class,
		String.class, String.class, Integer.class, Integer.class, Integer.class, String.class,
		Integer.class
		
	};
	
	public CustomStdTableModel() {
		DaoTable dao = new DaoInfoStudent();
		students = dao.selectDao();		
		daoDepart = new DaoDepart();
		daoHandi = new DaoHandicap();
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
		return students.size();
	}

	

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {		
		return true;
	}
	

	@Override
	public Object getValueAt(int row, int col) {
		InfoStudent std = (InfoStudent)students.get(row);		
		switch(col) {
		case 0 : return std.getIdx();
		case 1 : return std.getStdName();
		case 2 : return std.getJuminNum();
		case 3 : return std.getStartDate();
		case 4 : return std.getEndDate();
		case 5 : return std.getMobile();
		case 6 : return std.getTel();
		case 7 : return "  " + std.getStdAddr();
		case 8 : return std.getRoomNum() == 500?"통학":std.getRoomNum();
		case 9 : return daoHandi.selectTableById(std.getStdType());
		case 10 : return std.getGrade() + "급";
		case 11 : return "  " + std.getEmail();
		case 12 : Depart depart = (Depart)daoDepart.selectTableById(std.getDepartCode());
			return depart.getName();
		}
		return "";
	}

}
