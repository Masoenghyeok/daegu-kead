package kr.or.kead.ui.menu;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoTable;
import kr.or.kead.ui.stdmgn.CustomTableModel;
import kr.or.kead.ui.stdmgn.StdInsert;
import kr.or.kead.ui.stdmgn.StdList;

public class MenuMgn extends JMenuBar implements ActionListener {
	private JMenu stdMgn;			// �л� ����
	private JMenuItem stdAdd;		// �л� �߰�
	private JMenuItem stdDel;		// �л� ����
	private JMenuItem stdUpdate;	// �л� ����
	private JMenuItem stdList;		// �л� ����Ʈ
	private Container contentPane;
	private StdList listView;	
		
	public MenuMgn(Container contentPane) {
		super();
		this.contentPane = contentPane;
		init();
	}


	private void init() {
		stdMgn = new JMenu("�л� ����");
		stdAdd = new JMenuItem("�߰�");
		stdAdd.addActionListener(this);
		stdDel = new JMenuItem("����");
		stdDel.addActionListener(this);
		stdUpdate = new JMenuItem("����");
		stdUpdate.addActionListener(this);
		stdList = new JMenuItem("����Ʈ");
		stdList.addActionListener(this);
		
		stdMgn.add(stdAdd);
		stdMgn.add(stdDel);
		stdMgn.add(stdUpdate);
		stdMgn.add(stdList);
		
		add(stdMgn);		
		listView = new StdList(new CustomTableModel());
		contentPane.add(listView);
	}
	
	public void refreshList() {
		listView.setTableModel(new CustomTableModel());
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == stdAdd) {
			StdInsert launcher = new StdInsert(this);
			launcher.setVisible(true);			
		}else if(e.getSource() == stdDel) {
			String stdId = JOptionPane.showInputDialog(null, "�����Ͻ� �л��� ID�� �Է��ϼ���");
			if(stdId != null) {
				DaoTable dao = new DaoInfoStudent();
				InfoStudent std = new InfoStudent();
				std = dao.selectStudentById(Integer.parseInt(stdId));
				if(std.getIdx() != 0) {
					int confirmDel = JOptionPane.showConfirmDialog(
							null,
							std.getStdName() +" �л��� ���� �Ͻðڽ��ϱ�?",
							"�����޽���", 
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if(confirmDel == 0) {
						if(dao.deleteDao(Integer.parseInt(stdId))==0) {
							JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�.");
						}else {
							JOptionPane.showMessageDialog(null, "������ ���� ���� �ʾҽ��ϴ�.");
						}
					}					
				}				
			}			
		}else if(e.getSource() == stdUpdate) {
			String stdId = JOptionPane.showInputDialog(null, "�����Ͻ� �л��� ID�� �Է��ϼ���");
			if(stdId != null) {
				StdInsert stdUpdate = new StdInsert(Integer.parseInt(stdId), this);
				stdUpdate.setVisible(true);
			}		
		}else if(e.getSource() == stdList) {
			refreshList();	
		}		
	}

}
