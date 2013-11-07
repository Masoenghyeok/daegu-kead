package kr.or.kead.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginJoin extends JPanel implements ActionListener {
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnLogin;
	private JButton btnJoin;
	private JLabel lbl_id;
	private JTextField txtId;
	private JLabel lbl_pass;
	private JPasswordField txtPass;
	public LoginJoin() {
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(10, 10));
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 10, 10));
		
		lbl_id = new JLabel("아이디");
		lbl_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel.add(lbl_id);
		
		txtId = new JTextField();
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel.add(txtId);
		txtId.setColumns(10);
		
		lbl_pass = new JLabel("패스워드");
		lbl_pass.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pass.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel.add(lbl_pass);
		
		txtPass = new JPasswordField();
		txtPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtPass.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel.add(txtPass);
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(this);
		btnLogin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel_1.add(btnLogin);
		
		btnJoin = new JButton("회원가입");
		btnJoin.addActionListener(this);
		btnJoin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel_1.add(btnJoin);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnJoin) {
			btnJoinActionPerformed(arg0);
		}
		if (arg0.getSource() == btnLogin) {
			btnLoginActionPerformed(arg0);
		}
	}
	protected void btnLoginActionPerformed(ActionEvent arg0) {
	}
	protected void btnJoinActionPerformed(ActionEvent arg0) {
	}
}
