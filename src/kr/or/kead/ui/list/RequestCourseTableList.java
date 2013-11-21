package kr.or.kead.ui.list;

import kr.or.kead.domain.Auth;
import kr.or.kead.ui.list.model.RequestCourseCustomTableModel;

public class RequestCourseTableList extends AbsTableList {
	public RequestCourseTableList(int height, Auth auth) {
		super(new RequestCourseCustomTableModel(auth), height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setColumnSize() {
		Integer[] size={40,60,150,150,60,60,60};
		for(int i=0; i< size.length;i++) {
			columnSize.add(size[i]);
		}

	}

}
