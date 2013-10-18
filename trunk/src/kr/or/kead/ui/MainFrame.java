package kr.or.kead.ui;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import kr.or.kead.ui.menu.MenuMgn;

public class MainFrame extends JFrame {
	private MenuMgn menu;
	public MainFrame() throws HeadlessException {
		super();
		// menu
		
		menu = new MenuMgn(this.getContentPane());
		setJMenuBar(menu);
		
		
		setTitle("학사 관리");
		setSize(new Dimension(1120, 300));
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
	}
	
}
