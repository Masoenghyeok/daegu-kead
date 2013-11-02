package kr.or.kead.ui.list;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import kr.or.kead.ui.list.model.AbsCustomTableModel;
import kr.or.kead.ui.menu.MenuMgn;
import kr.or.kead.ui.stdmgn.CustomStdTableModel;
import kr.or.kead.ui.stdmgn.StdInsert;
import kr.or.kead.ui.stdmgn.StdList;

public abstract class AbsTableList extends JPanel{
	public JTable table;
	private AbsCustomTableModel model;	
	protected ArrayList<Integer> columnSize;
	private final boolean CENTER_SORT = true;
	private boolean[] sortColumn;	
	private JScrollPane scrollPane;
	private JMenuItem jmi, jmi1,jmi2;
	private int height;
	private JPopupMenu popMgn;
	private int  row;
	private int  col;
	
		
	public AbsTableList(AbsCustomTableModel model, int height, boolean[] sortColumn) {
		this.model = model;
		this.height = height;
		this.sortColumn = sortColumn;
		this.getLocationOnScreen();
		initialize();
	}
	
	public void setTableModel() {
		model.refreshData();
		setFullColumnSize();
	}

	private void initialize() {
		table = new JTable(model);
		table.setRowSelectionAllowed(true);
	  	table.setColumnSelectionAllowed(true);
		scrollPane = new JScrollPane(table);
		table.setColumnSelectionAllowed(false);
		setFullColumnSize();
		popMgnSet(this);
	}
	
	

	private void setFullColumnSize() {
		columnSize = new ArrayList<>();
		setColumnSize();
		TableColumnModel model = table.getColumnModel();
		int len = columnSize.size();
		int totalColumnSize = 0;
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
		for(int i=0;i<len;i++) {
			model.getColumn(i).setMaxWidth(columnSize.get(i));
			totalColumnSize +=model.getColumn(i).getMaxWidth();
			if(sortColumn[i] == CENTER_SORT) {
				model.getColumn(i).setCellRenderer(dtcrCenter);
			}else {
				model.getColumn(i).setCellRenderer(dtcrRight);
			}
		}
		setPreferredSize(new Dimension(totalColumnSize+20, height));
		scrollPane.setPreferredSize(new Dimension(totalColumnSize, height));
		add(scrollPane);
	}
	
	private void popMgnSet(final Component thisPanel) {
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
					Point point = thisPanel.getLocationOnScreen();					
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
	public abstract void setColumnSize();
	
	class popMgnClicked extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {			
			if(e.getSource()== jmi) {				
				StdInsert std = new StdInsert();				
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
					// d
					contentPane.refreshList(new StdList(new CustomStdTableModel(), contentPane));
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
