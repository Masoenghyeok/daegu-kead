package kr.or.kead.ui.list;

import kr.or.kead.ui.list.model.AbsCustomTableModel;
import kr.or.kead.ui.list.model.StdCustomTableModel;

public class StdTableList extends AbsTableList {
	private static boolean[] sortColumn = {false, true, true, true, true, true, false, true, true,
		true, true, false, false};
	public StdTableList(int height) {		
		super(new StdCustomTableModel(), height, sortColumn);	
	}

	@Override
	public void setColumnSize() {
		Integer[] size={30, 50, 100, 80, 80, 100, 100, 150, 50, 100, 50, 120, 100};
		for(int i=0; i< size.length;i++) {
			columnSize.add(size[i]);
		}
	}
}
