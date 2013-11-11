package kr.or.kead.ui;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import kr.or.kead.domain.Auth;
import kr.or.kead.ui.menu.MenuMgn;

public class MainFrame extends JFrame {
	private MenuMgn menu;
	public MainFrame(Auth auth) throws HeadlessException {
		super();
		// menu
		
		
		menu = new MenuMgn(this);
		menu.setAuth(auth);
		setJMenuBar(menu);
		
		menu.enableMenu();
		setTitle("대구직업능력개발원 학사관리");
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
	}
	
}
