package kr.or.kead.ui.stdmgn;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegJumin extends JPanel {
	private JTextField txtJumin1;
	private JTextField txtJumin2;

	/**
	 * Create the panel.
	 */
	public RegJumin() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtJumin1 = new JTextField();
		add(txtJumin1);
		txtJumin1.setColumns(6);
		
		JLabel lblJumin = new JLabel("-");
		add(lblJumin);
		
		txtJumin2 = new JTextField();
		add(txtJumin2);
		txtJumin2.setColumns(7);

	}
	
	
	
	public String getTxtJumin1() {
		return txtJumin1.getText();
	}



	public String getTxtJumin2() {
		return txtJumin2.getText();
	}



	public void setTxtJumin1(String jumin1, String jumin2) {
		this.txtJumin1.setText(jumin1);
		this.txtJumin2.setText(jumin2);
	}

}
