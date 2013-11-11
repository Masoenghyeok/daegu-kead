package kr.or.kead.ui.list.model;

public class CurriCustomModel extends AbsCustomTableModel {
	
	public CurriCustomModel() {
		sql = "select idx 번호, stdid 이름, depart 학과, profCode 교수 from curriculum";
	}
	

	@Override
	public Object getValueAt(int row, int col) {
		Object[] obj = arData.get(row);
		switch(col) {
		case 1: case 2: case 3: case 4: return obj[col];
		}		
		return null;
	}

}
