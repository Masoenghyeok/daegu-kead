package kr.or.kead.ui.menu;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoTable;
import kr.or.kead.ui.list.AbsTableList;
import kr.or.kead.ui.list.StdTableList;

public class MenuMgn extends JMenuBar  {	
	
	private Container contentPane;
	private AbsTableList listView; 
	private JMenu stdMenu;
	private JMenu departMenu;
	private JFrame frame;
	
		
	public MenuMgn(JFrame frame) {
		super();
		this.frame = frame;
		this.contentPane = frame.getContentPane();						
		init();
	}


	private void init() {	
		frame.setSize(new Dimension(1280, 400));
		stdMenu = new StdMenu(frame, (int)frame.getSize().getHeight());
		add(stdMenu);		
		
		departMenu = new DepartMenu(frame, (int)frame.getSize().getHeight());
		add(departMenu);
		listView = new StdTableList(400);
		contentPane.add(listView);
	}
}
