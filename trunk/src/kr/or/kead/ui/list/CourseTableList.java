package kr.or.kead.ui.list;

import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoHandicap;
import kr.or.kead.ui.list.model.AbsCustomTableModel;
import kr.or.kead.ui.list.model.CourseCustomTableModel;

public class CourseTableList extends AbsTableList {
	public CourseTableList(int height, String email) {
		super(new CourseCustomTableModel(email), height);	
	}

	@Override
	public void setColumnSize() {
		columnSize.add(60);
		columnSize.add(70);
		columnSize.add(150);
		columnSize.add(150);
		columnSize.add(60);

	}

}
