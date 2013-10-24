package kr.or.kead.ui.stdmgn;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegAddr extends JPanel implements ActionListener {
	private JTextField txtAddr;
	JButton btnAddr;

	/**
	 * Create the panel.
	 */
	public RegAddr() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtAddr = new JTextField();
		add(txtAddr);
		txtAddr.setColumns(30);
		
		btnAddr = new JButton("주소검색");
		btnAddr.addActionListener(this);
		add(btnAddr);		

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAddr) {
			btnAddrActionPerformed(arg0);
		}
	}
	protected void btnAddrActionPerformed(ActionEvent e) {
		
	}
}
