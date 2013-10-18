package kr.or.kead.ui.stdmgn;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class RegMobile extends JPanel {
	private JTextField txtSN;
	private JTextField txtTN;
	private JComboBox comboFN;

	/**
	 * Create the panel.
	 */
	public RegMobile() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		comboFN = new JComboBox();
		comboFN.setModel(new DefaultComboBoxModel(new String[] {"010", "011", "017", "019"}));
		add(comboFN);
		
		JLabel lblNewLabel = new JLabel("-");
		add(lblNewLabel);
		
		txtSN = new JTextField();
		add(txtSN);
		txtSN.setColumns(4);
		
		JLabel lblNewLabel_1 = new JLabel("-");
		add(lblNewLabel_1);
		
		txtTN = new JTextField();
		add(txtTN);
		txtTN.setColumns(4);
	}

	public JTextField getTxtSN() {
		return txtSN;
	}

	
	public JTextField getTxtTN() {
		return txtTN;
	}

	public JComboBox getComboFN() {
		return comboFN;
	}
	
	public void setMobile(int firstNum, String secondNum, String thirdNum) {
		this.comboFN.setSelectedIndex(firstNum);
		this.txtSN.setText(secondNum);
		this.txtTN.setText(thirdNum);
	}


}
