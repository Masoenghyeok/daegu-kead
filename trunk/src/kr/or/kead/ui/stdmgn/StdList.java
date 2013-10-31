package kr.or.kead.ui.stdmgn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.ui.menu.MenuMgn;

public class StdList extends JPanel {
	private JTable table;
	private JPopupMenu popMgn;
	private JMenuItem jmi, jmi1,jmi2;
	private MenuMgn contentPane;
	int  row;
	int  col;
	
	// 테이블에 CustomTableModel 에서 생성한 model을 읽어와 테이블에 세팅
	public StdList(CustomStdTableModel model, final MenuMgn contentPane) {
		this.contentPane = contentPane;
		this.table = new JTable(model);
	
	    table.setRowSelectionAllowed(true);
	  	table.setColumnSelectionAllowed(true);

		setColumnSize();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1100, 400));
		add(scrollPane);
		
		

		popMgn = new JPopupMenu();
		popMgn.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				popMgn.setVisible(false);
			}
			
		});
		jmi = new JMenuItem("저장");
		jmi1 = new JMenuItem("수정");
		jmi2 = new JMenuItem("삭제");
		jmi.addMouseListener(new popMgnClicked());	
		jmi1.addMouseListener(new popMgnClicked());
		jmi2.addMouseListener(new popMgnClicked());	
		popMgn.add(jmi);
		popMgn.add(jmi1);
		popMgn.add(jmi2);	
		
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {				
				if(e.getButton()==MouseEvent.BUTTON3) {					
					Point point = contentPane.getLocationOnScreen();					
					row = table.getSelectedRow();
					col = table.getSelectedColumn();					
					if(row >= 0 && col >=0) {						
						int xValue = e.getX();						
						int yValue = e.getY();						
						popMgn.show(null, xValue+point.x+10, yValue+point.y);
					}										
				}
			}	
		});		
	}	
	
	// 테이블 column 의 크기와 정렬을 설정
	private void setColumnSize() {
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer dtcrLeft = new DefaultTableCellRenderer();
		dtcrLeft.setHorizontalAlignment(SwingConstants.LEFT);
		TableColumnModel model = table.getColumnModel();
		model.getColumn(0).setPreferredWidth(10);
		model.getColumn(1).setPreferredWidth(30);
		model.getColumn(2).setPreferredWidth(60);
		model.getColumn(3).setPreferredWidth(25);
		model.getColumn(4).setPreferredWidth(25);
		model.getColumn(5).setPreferredWidth(40);
		model.getColumn(6).setPreferredWidth(40);
		model.getColumn(7).setPreferredWidth(120);
		model.getColumn(8).setPreferredWidth(15);
		model.getColumn(9).setPreferredWidth(20);
		model.getColumn(10).setPreferredWidth(20);
		model.getColumn(11).setPreferredWidth(100);	
		model.getColumn(12).setPreferredWidth(100);
		for(int i=0;i<model.getColumnCount();i++) {			
			if(i==7||i==11) {
				model.getColumn(i).setCellRenderer(dtcrLeft);
			}else {
				model.getColumn(i).setCellRenderer(dtcrRight);	
			}			
		}
	}
	
	// 메뉴에서 테이블의 값이 변경 되었을 때 다시 테이블을 수정해 
	// 출력 하기 위해 테이블 모델을 다시 읽어와 출력하는 메소드
	public void setTableModel(CustomStdTableModel model) {
		this.table.setModel(model);
		setColumnSize();
	}

	
	class popMgnClicked extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {			
			if(e.getSource()== jmi) {
				MenuMgn mgn = (MenuMgn) contentPane;
				StdInsert std = new StdInsert(mgn);				
			}else if(e.getSource()== jmi1) {				
				Object idx = table.getModel().getValueAt(row, 0);				
				MenuMgn mgn = (MenuMgn) contentPane;
				StdInsert std = new StdInsert((int)idx,mgn);				
			}else if(e.getSource() == jmi2) {
				Object idx = table.getModel().getValueAt(row, 0);
				if(new DaoInfoStudent().deleteDao((int)idx) == -1) {
					JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다.");										
				}else {
					JOptionPane.showMessageDialog(null, "삭제 하였습니다.");
					contentPane.removeAll();
					contentPane.refreshList();
					contentPane.updateUI();
				}
			}
			popMgn.setVisible(false);
		}

		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == jmi) {
				jmi.setBackground(Color.LIGHT_GRAY);
			}else if(e.getSource() == jmi1) {
				jmi1.setBackground(Color.LIGHT_GRAY);
			}else if(e.getSource() == jmi2) {
				jmi2.setBackground(Color.LIGHT_GRAY);
			}
		}		

		public void mouseExited(MouseEvent e) {
			if(e.getSource() == jmi) {
				jmi.setBackground(new Color(230,230,230));
			}else if(e.getSource() == jmi1) {
				jmi1.setBackground(new Color(230,230,230));
			}else if(e.getSource() == jmi2) {
				jmi2.setBackground(new Color(230,230,230));
			}
		}		
	}
}
