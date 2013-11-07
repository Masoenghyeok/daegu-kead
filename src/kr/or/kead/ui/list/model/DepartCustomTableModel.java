package kr.or.kead.ui.list.model;

import kr.or.kead.domain.Professor;
import kr.or.kead.service.DaoProfessor;


public class DepartCustomTableModel extends AbsCustomTableModel {
	
	public DepartCustomTableModel() {
		sql = "select code '번호', name '학과명', prof '교수명', tel '연락처' from depart";
		getResultSet();				
	}

	public Object getValueAt(int row, int col) {
		Object[] depart = arData.get(row);
		DaoProfessor daoProfessor = new DaoProfessor();
		System.out.println(col + " " + depart[col]);
		switch(col) {
		case 0:case 1: return depart[col];		
		case 2: Professor prof = (Professor)daoProfessor.selectTableById((int)depart[col]); 
			return prof.getName();   				// 추후 수정
		case 3: return depart[col];
		}
		
		return null;
	}

}
