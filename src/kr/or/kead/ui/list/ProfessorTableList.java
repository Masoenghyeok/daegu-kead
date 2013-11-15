package kr.or.kead.ui.list;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import kr.or.kead.ui.list.model.ProfessorCustomTableModel;

public class ProfessorTableList extends AbsTableList {
	
	public ProfessorTableList(int height, String email) {
		super(new ProfessorCustomTableModel(email), height);
	}

	@Override
	public void setColumnSize() {
//		TableColumn aColumn = new TableColumn();
//		  
//		aColumn.setHeaderValue("5번째 컬럼"); 
//		table.addColumn(aColumn);
//		aColumn = table.getColumn(4);		
//		aColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
		
		columnSize.add(40);
		columnSize.add(120);
		columnSize.add(80);
		columnSize.add(120);
		columnSize.add(120);
	}	
}
