package kr.or.kead.ui.list;

import kr.or.kead.ui.list.model.AbsCustomTableModel;
import kr.or.kead.ui.list.model.DepartCustomTableModel;

public class DepartTableList extends AbsTableList {
	private static boolean[] sortColumn = {true, true, true, true};
	public DepartTableList(int height) {		
		super(new DepartCustomTableModel(), height, sortColumn);
	}

	@Override
	public void setColumnSize() {
		Integer[] size={100, 200, 100, 300};
		for(int i=0; i< size.length;i++) {
			columnSize.add(size[i]);
		}
	}

}
