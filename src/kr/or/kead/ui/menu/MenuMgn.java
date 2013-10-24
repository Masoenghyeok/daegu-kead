package kr.or.kead.ui.menu;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JMenu stdMgn;			// 학생관리
	private JMenuItem stdAdd;		// 학생추가
	private JMenuItem stdDel;		// 학생삭제
	private JMenuItem stdUpdate;	// 학생수정
	private JMenuItem stdList;		// 학생 리스트
	private Container contentPane;
	private StdList listView;	
		
	public MenuMgn(Container contentPane) {
		super();
		this.contentPane = contentPane;
		init();
	}


	private void init() {
		stdMgn = new JMenu("학생관리");
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
			String stdId = JOptionPane.showInputDialog(null, "삭제 할 학생 ID를 입력하세요");
			if(stdId != null) {
				DaoTable dao = new DaoInfoStudent();
				InfoStudent std = new InfoStudent();
				std = (InfoStudent)dao.selectTableById(Integer.parseInt(stdId));
				if(std.getIdx() != 0) {
					int confirmDel = JOptionPane.showConfirmDialog(
							null,
							std.getStdName() +" 학생을 삭제 하시겠습니까?",
							"삭제메시지", 
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if(confirmDel == 0) {
						if(dao.deleteDao(Integer.parseInt(stdId))==0) {
							JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
							refreshList();
						}else {
							JOptionPane.showMessageDialog(null, "삭제가 완료 되지 않았습니다.");
						}
					}					
				}				
			}			
		}else if(e.getSource() == stdUpdate) {
			String stdId = JOptionPane.showInputDialog(null, "수정할 학생의 ID를 입력하세요.");
			if(stdId != null) {
				StdInsert stdUpdate = new StdInsert(Integer.parseInt(stdId), this);
				stdUpdate.setVisible(true);
			}		
		}else if(e.getSource() == stdList) {
			refreshList();	
		}		
	}

}
