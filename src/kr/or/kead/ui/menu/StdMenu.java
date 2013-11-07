package kr.or.kead.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.ui.insert_update.StdInsertUpdate;
import kr.or.kead.ui.list.AbsTableList;
import kr.or.kead.ui.list.StdTableList;

public class StdMenu extends AbsMenu {
	private AbsTableList stdListView;
	private DaoInfoStudent daoStd;	
	
	
	public StdMenu(JFrame frame, int height) {
		super(frame, "학생 관리");		
		stdListView = new StdTableList(height);
		daoStd = new DaoInfoStudent();
	}
	
	
	@Override
	protected void addMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		StdInsertUpdate insert = new StdInsertUpdate(null);		
		insert.setVisible(true);
		if(insert.showDialog() == 0) {		
			stdListView.setTableModel();
		}
	}
	
	@Override
	protected void delMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		int res = searchNum(daoStd, "삭제");
		if (res != -1 && daoStd.deleteDao(res) != -1) {			
			JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
			stdListView.setTableModel();
		} else {
			JOptionPane.showMessageDialog(null, "삭제 실패.");
		}

	}
		
	
	@Override
	protected void updateMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		int res = searchNum(daoStd, "수정");
		if(res != -1) {
			InfoStudent std = daoStd.selectTableById(res);
			StdInsertUpdate update = new StdInsertUpdate(std);
			update.setVisible(true);
			if (update.showDialog() == 0) stdListView.setTableModel();
		}
	}
	
	
	@Override
	protected void listMenuActionPerformed(ActionEvent e) {		
		refreshList(stdListView);
		
	}

}
