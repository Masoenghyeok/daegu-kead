package kr.or.kead.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.or.kead.domain.Depart;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.ui.insert_update.DepartInsertUpdate;
import kr.or.kead.ui.list.AbsTableList;
import kr.or.kead.ui.list.DepartTableList;

public class DepartMenu extends AbsMenu {
	private AbsTableList departListView;
	private DaoDepart daoDepart;
	
	public DepartMenu(JFrame frame, int height) {
		super(frame, "학과 관리");		
		daoDepart = new DaoDepart();
		departListView = new DepartTableList(height);
	}

	@Override
	protected void addMenuActionPerformed(ActionEvent e) {	
		listMenuActionPerformed(e);
		DepartInsertUpdate insert = new DepartInsertUpdate(null);			
		insert.setVisible(true);
		if (insert.showDialog()==0)departListView.setTableModel();
	}

	@Override
	protected void delMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		int res = searchNum(daoDepart, "삭제", 0);
		if (res != -1 && daoDepart.deleteDao(res) != -1) {			
			JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
			departListView.setTableModel();
		} else {
			JOptionPane.showMessageDialog(null, "삭제 실패.");
		}
	}

	@Override
	protected void updateMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		int res = searchNum(daoDepart, "수정", 0);
		if(res !=-1) {
			Depart depart = (Depart)daoDepart.selectTableById(res);
			DepartInsertUpdate update = new DepartInsertUpdate(depart);			
			update.setVisible(true);
			if (update.showDialog()==0)departListView.setTableModel();
		}

	}

	@Override
	protected void listMenuActionPerformed(ActionEvent e) {
		refreshList(departListView);
	}

}
