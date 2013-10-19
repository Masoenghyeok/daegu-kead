package kr.or.kead.ui.stdmgn;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class RegDate extends JPanel {
	private JTextField txtYear;
	private JTextField txtMonth;
	private JTextField txtDay;
	
	public RegDate() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtYear = new JTextField();
		add(txtYear);
		txtYear.setColumns(4);
		
		JLabel lbl_year = new JLabel("년");
		add(lbl_year);
		
		txtMonth = new JTextField();
		add(txtMonth);
		txtMonth.setColumns(2);
		
		JLabel lbl_month = new JLabel("월");
		add(lbl_month);
		
		txtDay = new JTextField();
		add(txtDay);
		txtDay.setColumns(2);
		
		JLabel lbl_day = new JLabel("일");
		add(lbl_day);

	}
	
	public String getTxtYear() {
		return this.txtYear.getText();
	}
	public String getTxtMonth() {
		return this.txtMonth.getText();
	}
	public String getTxtDay() {
		return this.txtDay.getText();
	}
	
	public void setTxtDate(String txtYear, String txtMonth, String txtDay ) {
		this.txtYear.setText(txtYear);
		this.txtMonth.setText(txtMonth);
		this.txtDay.setText(txtDay);
	}
	
	public String toStirng() {
		return String.format("%s-%s-%s", txtYear, txtMonth, txtDay);
	}

}
