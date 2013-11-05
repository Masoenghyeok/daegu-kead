package kr.or.kead.ui.list.model;

import kr.or.kead.domain.Depart;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoHandicap;

public class StdCustomTableModel extends AbsCustomTableModel {
	private DaoHandicap daoHandi;
	private DaoDepart daoDepart;
	
	
	public StdCustomTableModel() {
		sql = "select idx '번호', stdName '성명', juminNum '주민번호', startDate '입학날짜', endDate '수료날짜',"
				+ "mobile '휴대전화', tel '유선전화', stdAddr '주소', roomNum '통학/기숙사', stdType '장애유형', "
				+ "grade '등급', email '이메일', departCode '학과' from infostudent";
		getResultSet();
		daoHandi = new DaoHandicap();
		daoDepart = new DaoDepart();
	}


	public Object getValueAt(int row, int col) {
		Object[] std = arData.get(row);
		switch(col) {
		case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:
			return std[col];
		case 8: return (int)std[col] == 500?"통학":std[col];
		case 9: return daoHandi.selectTableById((int)std[col]);
		case 10: return std[col]+"등급";
		case 11: return std[col];
		case 12: Depart depart = (Depart)daoDepart.selectTableById((int)std[col]);
			return depart.getName();
		}
		return "";
	}

}
