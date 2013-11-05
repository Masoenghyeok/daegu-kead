package kr.or.kead.ui.list;

import kr.or.kead.ui.list.model.StdCustomTableModel;

public class StdTableList extends AbsTableList {

	public StdTableList(int height) {
		super(new StdCustomTableModel(), height);	
	}

	@Override
	public void setColumnSize() {
		Integer[] size={30,60,100,80,80,90,90,330,60,80,70,150,40};
		for(int i=0; i< size.length;i++) {
			columnSize.add(size[i]);
		}
	}
}
