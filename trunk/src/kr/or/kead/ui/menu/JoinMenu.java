package kr.or.kead.ui.menu;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinMenu extends JPanel implements ActionListener{
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnLogin;
	private JButton btnJoin;
	
	public JoinMenu() {
		initialize();
		
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 10));
		
		JPanel main_panel = new JPanel();
		add(main_panel, BorderLayout.CENTER);
		main_panel.setLayout(new GridLayout(2, 2, 10, 10));
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		main_panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		main_panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		main_panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		main_panel.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel btn_panel = new JPanel();
		add(btn_panel, BorderLayout.SOUTH);
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(this);
		btnLogin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btn_panel.add(btnLogin);
		
		btnJoin = new JButton("회원가입");
		btnJoin.addActionListener(this);
		btnJoin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btn_panel.add(btnJoin);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			actionPerformedBtnLogin(e);
		}
		else if (e.getSource() == btnJoin) {
			actionPerformedBtnJoin(e);
		}
	}
	protected void actionPerformedBtnJoin(ActionEvent e) {
	}
	protected void actionPerformedBtnLogin(ActionEvent e) {
	}
}
