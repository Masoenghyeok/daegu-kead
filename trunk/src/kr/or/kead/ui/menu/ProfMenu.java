package kr.or.kead.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.or.kead.domain.Professor;
import kr.or.kead.service.DaoProfessor;
import kr.or.kead.ui.insert_update.ProfessorInsertUpdate;
import kr.or.kead.ui.list.AbsTableList;
import kr.or.kead.ui.list.ProfessorTableList;

public class ProfMenu extends AbsMenu {
	private AbsTableList professorListView;
	private DaoProfessor daoProf;
	private ProfessorInsertUpdate profInsertUpdate;
	
	public ProfMenu(JFrame frame, int height) {
		super(frame, "교수 관리");
		professorListView = new ProfessorTableList(height, null);
		daoProf = new DaoProfessor();
	}

	@Override
	protected void addMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		profInsertUpdate = new ProfessorInsertUpdate(null);
		profInsertUpdate.setVisible(true);
		if(profInsertUpdate.showDialog()==0)professorListView.setTableModel();
	}

	@Override
	protected void delMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		int res = searchNum(daoProf, "삭제");
		if(res != -1 && daoProf.deleteDao(res) != -1) {
			JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
			professorListView.setTableModel();
		} else {
			JOptionPane.showMessageDialog(null, "삭제 실패.");
		}

	}

	@Override
	protected void updateMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		int res = searchNum(daoProf, "수정");
		if(res != -1) {
			Professor prof = (Professor)daoProf.selectTableById(res);
			profInsertUpdate = new ProfessorInsertUpdate(prof);
			profInsertUpdate.setVisible(true);
			if(profInsertUpdate.showDialog() == 0)professorListView.setTableModel();
		}

	}

	@Override
	protected void listMenuActionPerformed(ActionEvent e) {
		refreshList(professorListView);
	}

}
