package kr.or.kead.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import kr.or.kead.ui.list.AbsTableList;
import kr.or.kead.ui.list.ProfessorTableList;

public class ProfMenu extends AbsMenu {
	private AbsTableList professorListView;
	
	public ProfMenu(JFrame frame, int height) {
		super(frame, "교수 관리");
		professorListView = new ProfessorTableList(height);
	}

	@Override
	protected void addMenuActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void delMenuActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateMenuActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void listMenuActionPerformed(ActionEvent e) {
		refreshList(professorListView);
	}

}
