package kr.or.kead.ui.list;

import kr.or.kead.ui.list.model.AbsCustomTableModel;
import kr.or.kead.ui.list.model.DepartCustomTableModel;

public class DepartTableList extends AbsTableList {

	public DepartTableList(int height) {
		super(new DepartCustomTableModel(), height);		
	}

	@Override
	public void setColumnSize() {
		columnSize.add(40);
		columnSize.add(90);
		columnSize.add(90);
		columnSize.add(90);
	}

}
