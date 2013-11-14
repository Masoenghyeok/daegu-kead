package kr.or.kead.ui.list;

import kr.or.kead.domain.Auth;
import kr.or.kead.ui.list.model.LoginCustomTableModel;

public class LoginTableList extends AbsTableList {
	
	public LoginTableList(int height, Auth auth) {	
		super(new LoginCustomTableModel(auth), height);
		
	}

	@Override
	public void setColumnSize() {
		if(LoginCustomTableModel.auth.getLevel() == 1) {
			Integer[] size={30,60,100,80,80,90,90,330,60,80,70,150,60,40};
			for(int i=0; i< size.length;i++) {
				columnSize.add(size[i]);
			}
		}else {
			columnSize.add(40);
			columnSize.add(120);
			columnSize.add(80);
			columnSize.add(120);
			columnSize.add(120);
		}
		
		
	}
}
