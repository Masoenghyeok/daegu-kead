package kr.or.kead.ui.departmgn;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class DepartList extends JPanel {
private JTable table;	

	public DepartList(CustomDepartTableModel model) {
		this.table = new JTable(model);
		table.setColumnSelectionAllowed(false);			
//		table.getModel().addTableModelListener(this);		
		
		setColumnSize();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1100, 400));
		add(scrollPane);
		
	}
	
	// 테이블 column 의 크기와 정렬을 설정
	private void setColumnSize() {
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer dtcrLeft = new DefaultTableCellRenderer();
		dtcrLeft.setHorizontalAlignment(SwingConstants.LEFT);
		TableColumnModel model = table.getColumnModel();
		model.getColumn(0).setPreferredWidth(30);
		model.getColumn(1).setPreferredWidth(60);
		model.getColumn(2).setPreferredWidth(60);
		model.getColumn(3).setPreferredWidth(80);
		for(int i=0;i<model.getColumnCount();i++) {			
			model.getColumn(i).setCellRenderer(dtcrRight);						
		}
	}
	
	// 메뉴에서 테이블의 값이 변경 되었을 때 다시 테이블을 수정해 
	// 출력 하기 위해 테이블 모델을 다시 읽어와 출력하는 메소드
	public void setTableModel(CustomDepartTableModel model) {
		this.table.setModel(model);
		setColumnSize();
	}

	
	
}
