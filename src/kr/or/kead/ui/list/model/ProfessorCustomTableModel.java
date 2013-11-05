package kr.or.kead.ui.list.model;

public class ProfessorCustomTableModel extends AbsCustomTableModel {
	
	public ProfessorCustomTableModel() {
		sql = "select code '코드', name '교수명', depart '학과명', course '담당과목' from professor";
		getResultSet();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		// 추후 수정
		Object[] prof = arData.get(row);
		switch(col) {
		case 0:case 1:case 2:case 3: return prof[col];
		}
		return null;
	}

}
