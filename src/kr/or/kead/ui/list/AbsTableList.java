package kr.or.kead.ui.list;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import kr.or.kead.ui.list.model.AbsCustomTableModel;

public abstract class AbsTableList extends JPanel{
	public JTable table;
	private AbsCustomTableModel model;	
	protected ArrayList<Integer> columnSize;
	private final boolean CENTER_SORT = true;
	private boolean[] sortColumn;	
	private JScrollPane scrollPane;
	private int height;
	
		
	public AbsTableList(AbsCustomTableModel model, int height, boolean[] sortColumn) {
		this.model = model;
		this.height = height;
		this.sortColumn = sortColumn;
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
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		DefaultTableCellRenderer dtcrLeft = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		dtcrLeft.setHorizontalAlignment(SwingConstants.LEFT);
		for(int i=0;i<len;i++) {
			model.getColumn(i).setMaxWidth(columnSize.get(i));
			totalColumnSize +=model.getColumn(i).getMaxWidth();
			if(sortColumn[i] == CENTER_SORT) {
				model.getColumn(i).setCellRenderer(dtcrCenter);
			}else {
				model.getColumn(i).setCellRenderer(dtcrLeft);
			}
		}
		setPreferredSize(new Dimension(totalColumnSize+20, height));
		scrollPane.setPreferredSize(new Dimension(totalColumnSize, height));
		add(scrollPane);
	}

	public abstract void setColumnSize();

}
