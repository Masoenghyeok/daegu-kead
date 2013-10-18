package kr.or.kead.ui.stdmgn;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoTable;

public class CustomTableModel extends AbstractTableModel {
	private ArrayList<InfoStudent> students;
		
	// 테이블 각 열에 대한 이름
	private static final String[] columnNames = {"번호","이름","주민번호","입소일자","퇴소일자",
		"휴대폰","집","주소","통학/기숙","장애타입","장애등급","Email"};
	
	// 테이블 각 열에 대한 클래스
	private static final Class[] columnClasses = {
		Integer.class, String.class, String.class, String.class, String.class, String.class,
		String.class, String.class, Integer.class, Integer.class, Integer.class, String.class
	};
	
	public CustomTableModel() {
		DaoTable dao = new DaoInfoStudent();
		students = dao.selectDao();
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
	public Object getValueAt(int row, int col) {
		InfoStudent std = students.get(row);
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
		case 9 : return StdInsert.type[StdInsert.getType(std.getStdType())];
		case 10 : return std.getGrade() + "등급";
		case 11 : return "  " + std.getEmail();
		}
		return "";
	}

}
