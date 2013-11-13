package kr.or.kead.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.or.kead.domain.Auth;
import kr.or.kead.domain.InfoStudent;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.ui.LoginJoin;
import kr.or.kead.ui.MainFrame;
import kr.or.kead.ui.insert_update.StdInsertUpdate;

public class LogMenu extends AbsMenu {
	private JFrame frame;
	private Auth auth;
	private DaoInfoStudent daoStd;
	private InfoStudent std;
	
	public LogMenu(JFrame frame, int height) {
		super(frame, "로그인 관리");
		this.frame = frame;
		MainFrame mainFrame = (MainFrame)frame;
		auth = mainFrame.getAuth();
		this.setText(auth.getEmail());
		daoStd = new DaoInfoStudent();
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
		std = daoStd.selectTableByEmail(auth.getEmail());
		StdInsertUpdate insert = new StdInsertUpdate(std);
		insert.setVisible(true);
	}

	@Override
	protected void delMenuActionPerformed(ActionEvent e) {
		PasswdChange passwdChange = new PasswdChange(auth.getEmail());
		passwdChange.setVisible(true);
		
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
