package kr.or.kead.ui.menu;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import kr.or.kead.domain.Auth;
import kr.or.kead.ui.list.AbsTableList;
import kr.or.kead.ui.list.StdTableList;


public class MenuMgn extends JMenuBar  {	
	
	private Container contentPane;
	private AbsTableList listView; 
	private JMenu stdMenu;
	private JMenu departMenu;
	private JMenu profMenu;
	private JMenu logMenu;
	private JFrame frame;
	private Auth auth;
	
	
	public MenuMgn(JFrame frame) {
		super();
		this.frame = frame;		
		this.contentPane = frame.getContentPane();						
		init();
		
	}

	
	public void enableMenu() {	
		if(this.auth != null) {
			int level = this.auth.getLevel();
			if (level ==1) {			// 학생
				System.out.println("1");
				departMenu.setVisible(false);
				profMenu.setVisible(false);
			}else if(level == 2) {		// 교수
				System.out.println("2");
				stdMenu.setVisible(false);
				departMenu.setVisible(false);
			}
		}
		
	}


	private void init() {	
		frame.setSize(new Dimension(1280, 400));
		
		logMenu = new LogMenu(frame, (int)frame.getSize().getHeight());
		add(logMenu);
		
		stdMenu = new StdMenu(frame, (int)frame.getSize().getHeight());
		add(stdMenu);		
		
		departMenu = new DepartMenu(frame, (int)frame.getSize().getHeight());
		add(departMenu);		
		
		profMenu = new ProfMenu(frame, (int)frame.getSize().getHeight());
		add(profMenu);
		
//		stdMenu.setEnabled(false);
//		departMenu.setEnabled(false);
//		profMenu.setEnabled(false);
//		
		listView = new StdTableList(400);
//		contentPane.add(new LoginJoin());
		contentPane.add(listView);
		frame.pack();
	}


	public Auth getAuth() {
		return auth;
	}


	public void setAuth(Auth auth) {
		this.auth = auth;
	}
	
	
}
