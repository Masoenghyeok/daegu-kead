package kr.or.kead.ui.list.model;


public class DepartCustomTableModel extends AbsCustomTableModel {
	
	public DepartCustomTableModel() {
		sql = "select code '번호', name '학과명', prof '교수명', tel '연락처' from depart";
		getResultSet();				
	}

	public Object getValueAt(int row, int col) {
		Object[] depart = arData.get(row);
		switch(col) {
		case 0:case 1: return depart[col];
		case 2: return depart[col];   				// 추후 수정
		case 3: return depart[col];
		}
		
		return null;
	}

}
