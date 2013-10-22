package kr.or.kead.ui.stdmgn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.or.kead.domain.StdAddress;
import kr.or.kead.service.DaoAddr;
import kr.or.kead.service.DaoTable;

public class StdAddr extends JDialog implements ActionListener {
	private JTextField txtInput;
	private JButton btnSearch;
	private ArrayList<StdAddress> stdAddr;
	private JList list;
	private JPanel panel_1;
	private DefaultListModel<Object> listModel = new DefaultListModel<>();
	
	public StdAddr() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtInput = new JTextField();
		panel.add(txtInput);
		txtInput.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		panel.add(btnSearch);	
		list = new JList<>(listModel);
		list.setFixedCellWidth(200);
		panel_1 = new JPanel(new BorderLayout());
		ScrollPane scroll = new ScrollPane();
		scroll.add(list);
		panel_1.add(scroll, BorderLayout.CENTER);
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSearch) {
			btnSearchActionPerformed(arg0);
		}
	}
	protected void btnSearchActionPerformed(ActionEvent e) {
		DaoTable dao = new DaoAddr();
		stdAddr = dao.selectStdAddrByDong(txtInput.getText());
		int row = stdAddr.size();
				
		for(int i=0; i<row;i++) {			
			listModel.addElement(stdAddr.get(i).getSido()+" "+ 
						stdAddr.get(i).getGugun()+stdAddr.get(i).getDong());
		}
		
		list.setVisibleRowCount(10);
		
		
		getContentPane().validate();
	}
}
