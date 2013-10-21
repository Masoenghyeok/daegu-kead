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
		txtRoomNum.setEditable(false);

	}
	
	

	public String getTxtRoomNum() {
		return txtRoomNum.getText();
	}



	public void setTxtRoomNum(String txtRoomNum) {
		this.txtRoomNum.setText(txtRoomNum);
	}



	public boolean getRadioBtn1() {
		return radioBtn1.isSelected();
	}



	public void setRadioBtn1(boolean compare) {
		radioBtn1.setSelected(compare);
	}



	public boolean getRadioBtn2() {
		return radioBtn2.isSelected();
	}



	public void setRadioBtn2(boolean compare) {
		radioBtn2.setSelected(compare);
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
