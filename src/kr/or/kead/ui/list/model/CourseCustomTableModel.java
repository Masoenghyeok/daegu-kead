package kr.or.kead.ui.list.model;

import kr.or.kead.domain.Professor;
import kr.or.kead.service.DaoProfessor;

public class CourseCustomTableModel extends AbsCustomTableModel {
	private DaoProfessor daoprof;
	private Professor prof;
	public CourseCustomTableModel(String email) {
		if(email != null) {
			daoprof = new DaoProfessor();			
			prof = daoprof.selectTableByEmail(email);
			sql = "select * from view_course_depart_prof where 교수명 = '" + prof.getName() + "'" ;
			
		}else {
			sql = "select * from view_course_depart_prof";
		}
		
		getResultSet();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object[] rows = arData.get(row);
		switch(col) {
		case 0:case 1:case 2:case 3:case 4: return rows[col];
		}
		return null;
	}
	
	
}
