package kr.or.kead.ui.list.model;

public class DepartCustomTableModel extends AbsCustomTableModel {
	
	public DepartCustomTableModel() {
		sql = "select code '번호', name '학과명', prof '지도교수', tel '연락처' from depart";
		getResultSet();
	}
	@Override
	public Object getValueAt(int row, int col) {
		Object[] depart = arData.get(row);
		switch (col) {
		case 0:case 1:case 2:case 3: return depart[col];
		}
		return null;
	}

}
