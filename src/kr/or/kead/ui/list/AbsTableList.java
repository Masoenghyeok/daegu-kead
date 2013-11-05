package kr.or.kead.ui.list;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import kr.or.kead.ui.list.model.AbsCustomTableModel;

public abstract class AbsTableList extends JPanel{
	public JTable table;
	private AbsCustomTableModel model;
	protected ArrayList<Integer> columnSize;
	private JScrollPane scrollPane;
	private int height;
		
	public AbsTableList(AbsCustomTableModel model, int height) {
		this.model = model;
		this.height = height;
		initialize();
	}
	
	public void setTableModel() {		
		model.refreshData();
		setFullColumnSize();
	}

	private void initialize() {
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		table.setColumnSelectionAllowed(false);
		setFullColumnSize();
	}
	
	private void setFullColumnSize() {
		columnSize = new ArrayList<>();
		setColumnSize();
		TableColumnModel model = table.getColumnModel();
		int len = columnSize.size();
		int totalColumnSize = 0;
		for(int i=0;i<len;i++) {
			model.getColumn(i).setMaxWidth(columnSize.get(i));
			totalColumnSize +=model.getColumn(i).getMaxWidth();
		}
		setPreferredSize(new Dimension(totalColumnSize+20, height));
		scrollPane.setPreferredSize(new Dimension(totalColumnSize, height));
		add(scrollPane);
	}

	public abstract void setColumnSize();

}
