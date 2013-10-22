package kr.or.kead.ui.stdmgn;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
