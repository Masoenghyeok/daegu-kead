package kr.or.kead.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.or.kead.domain.Auth;
import kr.or.kead.service.DaoAuth;
import javax.swing.JPasswordField;

public class LoginJoin extends JFrame implements ActionListener {
	private JPanel title_panel;
	private JPanel email_panel;
	private JPanel passwd_panel;
	private JPanel btn_panel;
	private JLabel lblNewLabel;
	private JTextField txt_email;
	private JLabel lblNewLabel_1;
	private JButton btn_login;
	private JButton btn_join;
	private JButton btn_search_passwd;
	private JLabel lbl_title;
	private JPanel center_panel;
	private DaoAuth daoAuth;
	private Auth auth;
	private JPasswordField txt_passwd;
	
	public LoginJoin() {
		initialize();
		daoAuth = new DaoAuth();		
	}
	private void initialize() {
		setTitle("학사관리 시스템");
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		title_panel = new JPanel();
		getContentPane().add(title_panel, BorderLayout.NORTH);
		
		lbl_title = new JLabel("대구직업능력 개발원 학사 관리 시스템");
		lbl_title.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		title_panel.add(lbl_title);
		
		center_panel = new JPanel();
		getContentPane().add(center_panel, BorderLayout.CENTER);
		
		email_panel = new JPanel();
		center_panel.add(email_panel);
		email_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("이  메  일");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		email_panel.add(lblNewLabel);
		
		txt_email = new JTextField();
		txt_email.setHorizontalAlignment(SwingConstants.CENTER);
		txt_email.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		email_panel.add(txt_email);
		txt_email.setColumns(10);
		
		passwd_panel = new JPanel();
		center_panel.add(passwd_panel);
		passwd_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel_1 = new JLabel("비밀번호 ");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		passwd_panel.add(lblNewLabel_1);
		
		txt_passwd = new JPasswordField();
		txt_passwd.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		txt_passwd.setColumns(10);
		passwd_panel.add(txt_passwd);
		
		btn_panel = new JPanel();
		getContentPane().add(btn_panel, BorderLayout.SOUTH);
		
		btn_login = new JButton("로그인");
		btn_login.addActionListener(this);
		btn_login.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btn_panel.add(btn_login);
		
		btn_join = new JButton("회원가입");
		btn_join.addActionListener(this);
		btn_join.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btn_panel.add(btn_join);
		
		btn_search_passwd = new JButton("비밀번호찾기");
		btn_search_passwd.addActionListener(this);
		btn_search_passwd.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btn_panel.add(btn_search_passwd);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_search_passwd) {
			btn_search_passwdActionPerformed(e);
		}
		if (e.getSource() == btn_join) {
			btn_joinActionPerformed(e);
		}
		if (e.getSource() == btn_login) {
			btn_loginActionPerformed(e);
		}
	}
	protected void btn_loginActionPerformed(ActionEvent e) {
		if (isPasswdCorrect(txt_passwd.getPassword())) {
			StringTokenizer st = new StringTokenizer(
					(String)daoAuth.selectTableByEmail(txt_email.getText()).trim(), ":");
			auth = new Auth(st.nextToken(), Integer.parseInt(st.nextToken()));
			dispose();
			new MainFrame(auth);
		}else {
			JOptionPane.showMessageDialog(null, "아이디 또는 패스워드 불일치");
			txt_email.setText("");
			txt_passwd.setText("");
			txt_email.requestFocus();
			return;
		}		
	}
	protected void btn_joinActionPerformed(ActionEvent e) {
	}
	protected void btn_search_passwdActionPerformed(ActionEvent e) {
	}
	
	
	private boolean isPasswdCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPasswd = daoAuth.selectPasswdByEmail(txt_email.getText().trim()).toCharArray();
		if(input.length != correctPasswd.length) {
			isCorrect = false;
		}else {
			isCorrect = Arrays.equals(input, correctPasswd);
		}
		Arrays.fill(correctPasswd, '0');
		return isCorrect;
	}
}
