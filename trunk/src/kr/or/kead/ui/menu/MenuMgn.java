package kr.or.kead.ui.menu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import kr.or.kead.domain.Auth;
import kr.or.kead.ui.list.AbsTableList;
import kr.or.kead.ui.list.LoginTableList;
import kr.or.kead.ui.list.StdTableList;
import kr.or.kead.ui.list.model.LoginCustomTableModel;


public class MenuMgn extends JMenuBar  {	
	
	private Container contentPane;
	private AbsTableList listView;	
	private JMenu stdMenu;
	private JMenu departMenu;
	private JMenu profMenu;
	private JMenu logMenu;
	private JFrame frame;
	private Auth auth;
	private JPanel mainPanel;
	
	
	public MenuMgn(JFrame frame) {
		super();
		this.frame = frame;		
		this.contentPane = frame.getContentPane();				
		init();
		mainPanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				Image image = Toolkit.getDefaultToolkit().getImage("main.png");
				g.drawImage(image, 0, 0, this.getWidth() , this.getHeight(), this);
			}			
		};
		contentPane.add(mainPanel);								
		contentPane.validate();
		
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
			}else {
				listView = new StdTableList(400);
				contentPane.add(listView);
				frame.pack();
			}
			listView = new LoginTableList(80, auth);
			mainPanel.add(listView);
			frame.pack();
		}
		
	}


	private void init() {	
		frame.setSize(new Dimension(400, 250));
		Image image = Toolkit.getDefaultToolkit().getImage("symbol_1.gif");		
		frame.setIconImage(image);
		
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

//		frame.pack();
	}


	public Auth getAuth() {
		return auth;
	}


	public void setAuth(Auth auth) {
		this.auth = auth;
	}
	
	
}
