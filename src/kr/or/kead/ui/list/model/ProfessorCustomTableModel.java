package kr.or.kead.ui.list.model;

import java.awt.Checkbox;

import javax.swing.JCheckBox;

import kr.or.kead.domain.Depart;
import kr.or.kead.service.DaoDepart;

public class ProfessorCustomTableModel extends AbsCustomTableModel {
	private DaoDepart daoDepart;
	
	public ProfessorCustomTableModel(String email) {
		daoDepart = new DaoDepart();
		if(email != null) {
			sql = "select code '코드', name '교수명', depart '학과명', course '담당과목', email '이메일',"
					+ "passwd '비밀번호' from professor"
					+ " where email='" + email + "'";	
		}else {
			sql = "select code '코드', name '교수명', depart '학과명', course '담당과목', email '이메일',"
					+ "passwd '비밀번호' from professor";
		}
		
		
		getResultSet();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		// 추후 수정
		Object[] prof = arData.get(row);
		switch(col) {
		case 0:case 1:case 3:case 4:case 5:return prof[col];
		case 2:	Depart depart = (Depart)daoDepart.selectTableById((int)prof[col]);
				return depart.getName();
		case 6: return true;
		}
		return null;
	}

}
