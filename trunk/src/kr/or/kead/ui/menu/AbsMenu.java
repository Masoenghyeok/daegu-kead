package kr.or.kead.ui.menu;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.or.kead.service.DaoCourse;
import kr.or.kead.service.DaoTable;
import kr.or.kead.ui.list.AbsTableList;

public abstract class AbsMenu extends JMenu implements ActionListener{
	protected JMenuItem addMenu;
	protected JMenuItem delMenu;
	protected JMenuItem updateMenu;
	protected JMenuItem listMenu;	
	private JFrame frame;
	
	public AbsMenu(JFrame frame, String title) {
		this.frame = frame;
		initialize(title);
	}
	private void initialize(String title) {
		setText(title);
		addMenu = new JMenuItem("추가");
		addMenu.addActionListener(this);
		add(addMenu);
		
		delMenu = new JMenuItem("삭제");
		delMenu.addActionListener(this);
		add(delMenu);
		
		updateMenu = new JMenuItem("수정");
		updateMenu.addActionListener(this);
		add(updateMenu);
		
		listMenu = new JMenuItem("리스트");
		listMenu.addActionListener(this);
		add(listMenu);
	}
	
	public void refreshList(AbsTableList list) {
		Container contentPane = frame.getContentPane();
		contentPane.removeAll();		
		list.setTableModel();
		contentPane.add(list);
		setFrameSize(list);
		contentPane.validate();
		frame.pack();
	}
	private void setFrameSize(JPanel panel) {
		this.frame.setSize(panel.getPreferredSize());
		this.frame.validate();
	}
	
	public int searchNum(DaoTable daoTable, String msg, int code) {
		ArrayList<String> lists;
		if(code == 0) {
			lists = daoTable.selectTableAllList();
		}else {
			lists = daoTable.selectTableAllListByCode(code);
		}
		
		
		Object[] arLists = lists.toArray();
		int result = -1;
		try {
			String res = (String) JOptionPane.showInputDialog(null, msg+" 하고자 하는 번호 선택",
					msg, JOptionPane.OK_CANCEL_OPTION, null, arLists, arLists[0]);
			StringTokenizer st = new StringTokenizer(res, ":");
			result = Integer.parseInt(st.nextToken().trim());
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "취소");
		}
		return result;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addMenu) {
			addMenuActionPerformed(e);			
		}
		else if (e.getSource() == delMenu) {
			delMenuActionPerformed(e);
		}
		else if (e.getSource() == updateMenu) {
			updateMenuActionPerformed(e);			
		}
		else if (e.getSource() == listMenu) {
			listMenuActionPerformed(e);
		}
	}
	protected abstract void addMenuActionPerformed(ActionEvent e);
	protected abstract void delMenuActionPerformed(ActionEvent e);
	protected abstract void updateMenuActionPerformed(ActionEvent e);
	protected abstract void listMenuActionPerformed(ActionEvent e);
}
