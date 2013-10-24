package kr.or.kead.ui.stdmgn;

import java.awt.Dimension;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	
	public StdList(CustomTableModel model) {
		this.table = new JTable(model);
		table.setColumnSelectionAllowed(false);			
		table.getModel().addTableModelListener(this);		
		
		setColumnSize();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1100, 400));
		add(scrollPane);
		
	}
	
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
	
	public void setTableModel(CustomTableModel model) {
		this.table.setModel(model);
		setColumnSize();
	}

	
	@Override
	public void tableChanged(TableModelEvent e) {
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
