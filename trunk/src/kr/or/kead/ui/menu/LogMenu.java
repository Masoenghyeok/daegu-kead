package kr.or.kead.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.or.kead.domain.Auth;
import kr.or.kead.ui.LoginJoin;

public class LogMenu extends AbsMenu {
	private JFrame frame;
	private Auth auth;
	public LogMenu(JFrame frame, int height, Auth auth) {
		super(frame, "로그인 관리");
		this.frame = frame;
		this.auth = auth;
		init();
	}

	private void init() {
		addMenu.setText("사용자정보");
		delMenu.setText("비밀번호 변경");
		updateMenu.setText("로그아웃");
		listMenu.setText("종료");
	}

	@Override
	protected void addMenuActionPerformed(ActionEvent e) {
		
	}

	@Override
	protected void delMenuActionPerformed(ActionEvent e) {
		String rePasswd = JOptionPane.showInputDialog(null, "변경하실 비밀번호를 입력하세요");
		
	}
	
	
	@Override
	protected void updateMenuActionPerformed(ActionEvent e) {
		new LoginJoin();
		frame.dispose();
	}

	@Override
	protected void listMenuActionPerformed(ActionEvent e) {
		frame.dispose();

	}

}
