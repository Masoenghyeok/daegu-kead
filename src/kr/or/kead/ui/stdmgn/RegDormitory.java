package kr.or.kead.ui.stdmgn;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegDormitory extends JPanel implements ActionListener {
	private JTextField txtRoomNum;
	private JRadioButton radioBtn1;
	private JRadioButton radioBtn2;

	/**
	 * Create the panel.
	 */
	public RegDormitory() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		radioBtn1 = new JRadioButton("통학", true);
		radioBtn1.addActionListener(this);
		add(radioBtn1);
		
		radioBtn2 = new JRadioButton("기숙사");
		radioBtn2.addActionListener(this);
		add(radioBtn2);
		
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(radioBtn1);
		bg.add(radioBtn2);
		
		txtRoomNum = new JTextField();
		add(txtRoomNum);
		txtRoomNum.setColumns(10);

	}
	
	

	public JTextField getTxtRoomNum() {
		return txtRoomNum;
	}



	public void setTxtRoomNum(JTextField txtRoomNum) {
		this.txtRoomNum = txtRoomNum;
	}



	public JRadioButton getRadioBtn1() {
		return radioBtn1;
	}



	public void setRadioBtn1(JRadioButton radioBtn1) {
		this.radioBtn1 = radioBtn1;
	}



	public JRadioButton getRadioBtn2() {
		return radioBtn2;
	}



	public void setRadioBtn2(JRadioButton radioBtn2) {
		this.radioBtn2 = radioBtn2;
	}



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == radioBtn1) {
			radioBtn1ActionPerformed(e);
		}else if (e.getSource() == radioBtn2) {
			radioBtn2ActionPerformed(e);
		}
	}

	protected void radioBtn1ActionPerformed(ActionEvent e) {
		txtRoomNum.setEditable(false);
	}
	
	private void radioBtn2ActionPerformed(ActionEvent e) {
		txtRoomNum.setEditable(true);
	}
}
