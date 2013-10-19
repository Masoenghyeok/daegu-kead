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
	
	static final String[] mobileNum={
		"010","011","017","019"
	};
	
	static final String[] telNum={
		"02", "031", "032", "033", "041", "042", "043", "044",
		"051", "052", "053", "054", "055", "061", "062", "063", "064"
	};

	/**
	 * Create the panel.
	 */
	public RegMobile(int flag) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		comboFN = new JComboBox();
		if(flag==1) {
			comboFN.setModel(new DefaultComboBoxModel(mobileNum));
		}else {
			comboFN.setModel(new DefaultComboBoxModel(telNum));
		}
		
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

	public String getTxtSN() {
		return txtSN.getText();
	}

	
	public String getTxtTN() {
		return txtTN.getText();
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
