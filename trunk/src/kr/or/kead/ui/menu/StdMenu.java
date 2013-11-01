package kr.or.kead.ui.menu;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.ui.list.AbsTableList;
import kr.or.kead.ui.list.DepartTableList;
import kr.or.kead.ui.list.StdTableList;

public class StdMenu extends AbsMenu {
	private AbsTableList stdListView;
	private DaoInfoStudent daoStd;
	
	public StdMenu(Container contentPane) {
		super(contentPane, "학생 관리");
		// 수정 필요 
//		stdListView = new StdList(new CustomStdTableModel());
		daoStd = new DaoInfoStudent();
	}
	
	@Override
	protected void addMenuActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "삽입되었다 치고.");
//		JDialog insert = new StdInsert();
//		insert.setVisible(true);
	}


	@Override
	protected void delMenuActionPerformed(ActionEvent e) {
		int res = searchNum(daoStd, "삭제");
		if (res != -1 && daoStd.deleteDao(res) != -1) {
			stdListView.setTableModel();
			JOptionPane.showMessageDialog(null, "삭제 되었습니다.");			
		} else {
			JOptionPane.showMessageDialog(null, "삭제 실패.");
		}

	}
		
	@Override
	protected void updateMenuActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "수정되었다 치고.");
		int res = searchNum(daoStd, "수정");
		if(res != -1) {
			InfoStudent std = daoStd.selectTableById(res);
//			JDialog update = new StdInsert(std, menuMgn);
		}
	}
	
	@Override
	protected void listMenuActionPerformed(ActionEvent e) {
		stdListView = new StdTableList(500);
		refreshList(stdListView);		
	}

}
