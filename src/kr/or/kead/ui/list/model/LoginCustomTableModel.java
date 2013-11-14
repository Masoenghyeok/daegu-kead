package kr.or.kead.ui.list.model;

import kr.or.kead.domain.Auth;
import kr.or.kead.domain.Depart;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoHandicap;

public class LoginCustomTableModel extends AbsCustomTableModel {
	private DaoHandicap daoHandi;
	private DaoDepart daoDepart;
	public static Auth auth;
	
	public LoginCustomTableModel(Auth auth) {
		this.auth = auth;
		if(this.auth.getLevel() == 1) {
			sql = "select idx '번호', stdName '성명', juminNum '주민번호', startDate '입학날짜', endDate '수료날짜',"
				+ "mobile '휴대전화', tel '유선전화', stdAddr '주소', roomNum '통학/기숙사', stdType '장애유형', "
				+ "grade '등급', email '이메일', passwd '비밀번호', departCode '학과' from infostudent where email = '" + this.auth.getEmail() +"'" ;
		}else {
			sql = "select code '코드', name '교수명', depart '학과명', course '담당과목', email '이메일',"
					+ "passwd '비밀번호' from professor where email ='" + auth.getEmail() + "'";
		}
		getResultSet();
		daoHandi = new DaoHandicap();
		daoDepart = new DaoDepart();
	}


	public Object getValueAt(int row, int col) {
		Object[] obj = arData.get(row);
		if(auth.getLevel() == 1) {
			switch(col) {
			case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:
				return obj[col];
			case 8: return (int)obj[col] == 500?"통학":obj[col];
			case 9: return daoHandi.selectTableById((int)obj[col]);
			case 10: return obj[col]+"등급";
			case 11: return obj[col];
			case 12: 				
					if(obj[col] != null) {
						StringBuffer sPass = new StringBuffer((String)obj[col]);					
						int len = sPass.length();
						String resultString = "";
						for(int i=0;i<len;i++) {
							resultString += sPass.replace(0, len, "*");
						}					
						return resultString;
					}				
					return obj[col];
			case 13: Depart depart = (Depart)daoDepart.selectTableById((int)obj[col]);
				return depart.getName();
			}
			return "";
		}else {
			
			switch(col) {
			case 0:case 1:case 2:case 3:case 4:case 5:return obj[col];
			case 6: return true;
			}
			return null;
		}
		
	}

}
