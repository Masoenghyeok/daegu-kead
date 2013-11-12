package kr.or.kead.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import kr.or.kead.ui.LoginJoin;

public class LogMenu extends AbsMenu {

	private JFrame frame;
	public LogMenu(JFrame frame, int height) {
		super(frame, "로그인 관리");
		this.frame = frame;
		init();
	}

	private void init() {
		addMenu.setText("로그아웃");
		delMenu.setText("종료");
		updateMenu.setVisible(false);
		listMenu.setVisible(false);
	}

	@Override
	protected void addMenuActionPerformed(ActionEvent e) {
		new LoginJoin();
		frame.dispose();
	}

	@Override
	protected void delMenuActionPerformed(ActionEvent e) {
		frame.dispose();
	}
	
	
	@Override
	protected void updateMenuActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void listMenuActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
