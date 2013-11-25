package kr.or.kead.ui.list.model;

import kr.or.kead.domain.Auth;

public class RequestCourseCustomTableModel extends AbsCustomTableModel {	
	public RequestCourseCustomTableModel(Auth auth) {
		int level;
		if(auth != null) {
			level = auth.getLevel();
		}else {
			level =3;
		}
		
		String where = "";
		if(level ==2) {
			where = " where 교수이메일='" + auth.getEmail() + "'";		
		}else if(level ==1) {
			where  = " where 학생이메일='" + auth.getEmail() + "'";			
		}
		sql = "select 번호, 학생명, 과목명, 교제,학과,담당교수, 학점 from view_request_infostd_course"
				+ where;
		getResultSet();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object[] obj = arData.get(row);
		switch(col) {
		case 0:case 1:case 2:case 3:case 4:case 5:case 6: return obj[col];
		}
		return obj[col];
	}
	
}
