package kr.or.kead.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class ProfMenu extends AbsMenu {
	private 

	public ProfMenu(JFrame frame, int height) {
		super(frame, "교수 관리");
		
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
		refreshList();
	}

}
