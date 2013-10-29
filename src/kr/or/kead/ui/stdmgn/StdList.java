package kr.or.kead.ui.stdmgn;

import java.awt.Dimension;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoTable;

public class StdList extends JPanel implements TableModelListener {
	private JTable table;
	
	// 테이블에 CustomTableModel 에서 생성한 model을 읽어와 테이블에 세팅
	public StdList(CustomStdTableModel model) {
		this.table = new JTable(model);
		table.setColumnSelectionAllowed(true);
//		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setRowSelectionAllowed(true);
//	    table.isCellEditable(0, 0);		
//		table.setEnabled(true);
		
		table.addTableModelListener(this);
		
		table.editCellAt(2, 2);
		
//		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

	
	@Override
	public void tableChanged(TableModelEvent e) {
		System.out.println(" table model in");
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel)e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);
		System.out.println("column = " + columnName + " data = " + data + " row = " + row);
		Object idx = model.getValueAt(row, 0);
		System.out.println(idx);
		DaoTable dao = new DaoInfoStudent();		
		InfoStudent std = new InfoStudent((int)idx,
				(String)model.getValueAt(row, 1),
				(String)model.getValueAt(row, 2),
				(Date)model.getValueAt(row, 3),
				(Date)model.getValueAt(row, 4), 
				(String)model.getValueAt(row, 5),
				(String)model.getValueAt(row, 6),
				(String)model.getValueAt(row, 7),
				Integer.parseInt((String)model.getValueAt(row, 8)),
				Integer.parseInt((String)model.getValueAt(row, 9)),
				Integer.parseInt((String)model.getValueAt(row, 10)),
				(String)model.getValueAt(row, 11),
				Integer.parseInt((String)model.getValueAt(row, 12)));
		if(dao.updateDao(std) == 0) {
			JOptionPane.showMessageDialog(null, "수정되었습니다.");
		}else {
			JOptionPane.showMessageDialog(null, "수정을 완료 하지 못하였습니다.");
		}		
	}

}
