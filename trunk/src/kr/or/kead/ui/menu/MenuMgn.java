package kr.or.kead.ui.menu;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoTable;
import kr.or.kead.ui.departmgn.CustomDepartTableModel;
import kr.or.kead.ui.departmgn.DepartInsert;
import kr.or.kead.ui.departmgn.DepartList;
import kr.or.kead.ui.stdmgn.CustomStdTableModel;
import kr.or.kead.ui.stdmgn.StdInsert;
import kr.or.kead.ui.stdmgn.StdList;

public class MenuMgn extends JMenuBar implements ActionListener {
	private JMenu stdMgn;			// 학생관리
	private JMenuItem stdAdd;		// 학생추가
	private JMenuItem stdDel;		// 학생삭제
	private JMenuItem stdUpdate;	// 학생수정
	private JMenuItem stdList;		// 학생 리스트
	
	private JMenu departMgn;        // 학과 관리
	private JMenuItem departAdd;	// 학과 추가	
	private JMenuItem departDel;	// 학과 삭제
	private JMenuItem departUpdate;	// 학과 수정	
	private JMenuItem departList;	// 학과 리스트
	private DepartList departListView;
	
	private Container contentPane;
	private StdList listView;
	
	private DaoTable dao;
	private StdInsert stdIn;
	private DaoDepart daoDepart;
	private DepartInsert depart; 
		
	public MenuMgn(Container contentPane) {
		super();
		this.contentPane = contentPane;
		dao = new DaoInfoStudent();
		daoDepart = new DaoDepart();
		init();
	}


	private void init() {		
		stdSetting();
		departSetting();
	}	

	private void stdSetting() {
		stdMgn = new JMenu("학생관리");
		stdMgn.setHorizontalAlignment(SwingConstants.CENTER);
		stdAdd = new JMenuItem("추가");
		stdAdd.addActionListener(this);
		stdDel = new JMenuItem("삭제");
		stdDel.addActionListener(this);
		stdUpdate = new JMenuItem("수정");
		stdUpdate.addActionListener(this);
		stdList = new JMenuItem("리스트");
		stdList.addActionListener(this);
		
		stdMgn.add(stdAdd);
		stdMgn.add(stdDel);
		stdMgn.add(stdUpdate);
		stdMgn.add(stdList);
		
		add(stdMgn);		
		listView = new StdList(new CustomStdTableModel(), this);
		contentPane.add(listView);		
	}

	private void departSetting() {
		departMgn = new JMenu("학과 관리");
		departMgn.setHorizontalAlignment(SwingConstants.CENTER);
		departAdd = new JMenuItem("추가");
		departAdd.addActionListener(this);
		departDel = new JMenuItem("삭제");
		departDel.addActionListener(this);
		departUpdate = new JMenuItem("수정");
		departUpdate.addActionListener(this);
		departList = new JMenuItem("리스트");
		departList.addActionListener(this);
		
		departMgn.add(departAdd);
		departMgn.add(departDel);
		departMgn.add(departUpdate);
		departMgn.add(departList);
		
		add(departMgn);		
	}

	public void refreshList() {		
		contentPane.removeAll();
		listView.setTableModel(new CustomStdTableModel());
		contentPane.add(listView);
		contentPane.validate();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == stdAdd) {
			stdIn = new StdInsert(this);
			stdIn.setVisible(true);			
		}else if(e.getSource() == stdDel) {
			int res = searchNum(dao,"삭제");
			if(res != -1 && dao.deleteDao(res) != -1) {
				refreshList();
				JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
			}else {
				JOptionPane.showMessageDialog(null, "삭제가 완료 되지 않았습니다.");							
			}									
		}else if(e.getSource() == stdUpdate) {
			int res = searchNum(dao, "수정");	
			if(res != -1) {				
				stdIn = new StdInsert(res, this);
				stdIn.setVisible(true);				
			}		
		}else if(e.getSource() == stdList) {		
			refreshList();				
		}else if(e.getSource() == departList) {			
			departListView = new DepartList(new CustomDepartTableModel());
			contentPane.removeAll();
			contentPane.add(departListView);
			contentPane.validate();			
		}else if(e.getSource() == departAdd) {
			depart = new DepartInsert(this);
			depart.setVisible(true);
		}else if(e.getSource() == departUpdate) {			
			int code = searchNum(daoDepart, "수정");
			if(code != -1) {
				depart = new DepartInsert(code, this);
			}			
			depart.setVisible(true);
		}
	}
	
	public int searchNum(DaoTable daoTable, String msg) {
		ArrayList<String> lists = daoTable.selectTableAllList();
		Object[] arLists = lists.toArray();
		int result = -1;
		try {
			String res = (String) JOptionPane.showInputDialog(null, msg+" 하고자 하는 번호 선택",
					msg, JOptionPane.OK_CANCEL_OPTION, null, arLists, arLists[0]);
			StringTokenizer st = new StringTokenizer(res, ":");
			result = Integer.parseInt(st.nextToken());
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "취소");
		}
		return result;
	}

}
