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
	
	public ProfessorTableList(int height) {
		super(new ProfessorCustomTableModel(), height);
	}

	@Override
	public void setColumnSize() {	
		columnSize.add(40);
		columnSize.add(120);
		columnSize.add(80);
		columnSize.add(120);
	}	
}
