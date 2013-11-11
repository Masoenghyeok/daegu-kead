package kr.or.kead.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.or.kead.domain.Auth;
import kr.or.kead.service.DaoAuth;
import kr.or.kead.ui.insert_update.ProfessorInsertUpdate;
import kr.or.kead.ui.insert_update.StdInsertUpdate;

public class LoginJoin extends JFrame implements ActionListener {
	private JPanel stdLoginPanel;
	private JPanel stdBtnPanel;
	private JButton btnStdLogin;
	private JButton btnStdJoin;
	private JLabel lblStdId;
	private JTextField txtStdId;
	private JLabel lblStdPass;
	private JPasswordField txtStdPass;
	private JTabbedPane tabMenu;
	private JPanel stdPanel;
	
	private JPanel profPanel;
	private Image img;
	private ImageIcon icon;
	private JPanel stdIdPanel;
	private JPanel stdPassPanel;
	private JPanel profLoginPanel;
	private JPanel profIdPanel;
	private JLabel lblProfId;
	private JTextField txtprofId;
	private JPanel profPassPanel;
	private JLabel lblProfPass;
	private JPasswordField passProf;
	private JPanel profBtnPanel;
	private JButton btnProfLogin;
	private JButton btnProfJoin;
	private DaoAuth daoAuth;
	private Auth auth;
	
	public LoginJoin() {
		initialize();
		daoAuth = new DaoAuth();
	}
	private void initialize() {
		setLayout(new BorderLayout(10, 10));
		
		tabMenu = new JTabbedPane(JTabbedPane.BOTTOM);		
		tabMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(tabMenu, BorderLayout.CENTER);		
		
		stdTabPanelMake();
		profTabPanelMake();
		setVisible(true);
	
	}
	
	private void stdTabPanelMake() {
		img = Toolkit.getDefaultToolkit().getImage("kead.jpg");				
		stdPanel = new imagePanel(img);	
		tabMenu.addTab("학생", null, stdPanel, null);
		stdPanel.setLayout(new BorderLayout(0, 0));
		
		Color color = new Color(00, 33, 33, 33);
		
		stdLoginPanel = new JPanel();		
		stdPanel.add(stdLoginPanel, BorderLayout.CENTER);
		stdLoginPanel.setLayout(new GridLayout(2, 1, 10, 10));		
		stdLoginPanel.setBackground(color);
		
		stdIdPanel = new JPanel();
		stdIdPanel.setBackground(color);
		stdLoginPanel.add(stdIdPanel);
		
		lblStdId = new JLabel("아이디");		
		stdIdPanel.add(lblStdId);
		lblStdId.setForeground(Color.GREEN);
		lblStdId.setHorizontalAlignment(SwingConstants.CENTER);
		lblStdId.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		
		txtStdId = new JTextField();
		stdIdPanel.add(txtStdId);
		txtStdId.setHorizontalAlignment(SwingConstants.CENTER);
		txtStdId.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		txtStdId.setColumns(10);
		
		stdPassPanel = new JPanel();
		stdLoginPanel.add(stdPassPanel);
		stdPassPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		stdPassPanel.setBackground(color);
		
		lblStdPass = new JLabel("패스워드");
		stdPassPanel.add(lblStdPass);
		lblStdPass.setForeground(Color.GREEN);
		lblStdPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblStdPass.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		
		txtStdPass = new JPasswordField();
		txtStdPass.setColumns(10);
		stdPassPanel.add(txtStdPass);
		txtStdPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtStdPass.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
				
		stdBtnPanel = new JPanel();		
		stdPanel.add(stdBtnPanel, BorderLayout.SOUTH);
		stdBtnPanel.setBackground(color);
				
		btnStdLogin = new JButton("로그인");
		btnStdLogin.addActionListener(this);
		btnStdLogin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));		
		stdBtnPanel.add(btnStdLogin);
		
		btnStdJoin = new JButton("회원가입");
		btnStdJoin.addActionListener(this);
		btnStdJoin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));		
		stdBtnPanel.add(btnStdJoin);		
	}
	
	private void profTabPanelMake() {
		img = Toolkit.getDefaultToolkit().getImage("kead2.jpg");		
		profPanel = new imagePanel(img);		
		
		tabMenu.addTab("교수", null, profPanel, null);
		profPanel.setLayout(new BorderLayout(0, 0));
		
		profLoginPanel = new JPanel();
		profLoginPanel.setBackground(new Color(0, 33, 33, 33));
		profPanel.add(profLoginPanel, BorderLayout.CENTER);
		profLoginPanel.setLayout(new GridLayout(2, 1, 10, 10));
		
		profIdPanel = new JPanel();
		profIdPanel.setBackground(new Color(0, 33, 33, 33));
		profLoginPanel.add(profIdPanel);
		
		lblProfId = new JLabel("아이디");
		lblProfId.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfId.setForeground(Color.GREEN);
		lblProfId.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		profIdPanel.add(lblProfId);
		
		txtprofId = new JTextField();
		txtprofId.setHorizontalAlignment(SwingConstants.CENTER);
		txtprofId.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		txtprofId.setColumns(10);
		profIdPanel.add(txtprofId);
		
		profPassPanel = new JPanel();
		profPassPanel.setBackground(new Color(0, 33, 33, 33));
		profLoginPanel.add(profPassPanel);
		profPassPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblProfPass = new JLabel("패스워드");
		lblProfPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfPass.setForeground(Color.GREEN);
		lblProfPass.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		profPassPanel.add(lblProfPass);
		
		passProf = new JPasswordField();
		passProf.setHorizontalAlignment(SwingConstants.CENTER);
		passProf.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		passProf.setColumns(10);
		profPassPanel.add(passProf);
		
		profBtnPanel = new JPanel();
		profBtnPanel.setBackground(new Color(0, 33, 33, 33));
		profPanel.add(profBtnPanel, BorderLayout.SOUTH);
		
		btnProfLogin = new JButton("로그인");
		btnProfLogin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnProfLogin.addActionListener(this);
		profBtnPanel.add(btnProfLogin);
		
		btnProfJoin = new JButton("회원가입");
		btnProfJoin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnProfJoin.addActionListener(this);
		profBtnPanel.add(btnProfJoin);		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStdJoin) {
			btnStdJoinActionPerformed(e);
		}else if (e.getSource() == btnStdLogin) {
			btnStdLoginActionPerformed(e);
		}else if (e.getSource() == btnProfLogin) {
			btnProfLoginActionPerformed(e);
		}else if (e.getSource() == btnProfJoin) {
			btnProfJoinActionPerformed(e);
		}
	}
	
	protected void btnStdLoginActionPerformed(ActionEvent e) {
		if (isPasswdCorrect(txtStdPass.getPassword())) {
			StringTokenizer st = new StringTokenizer(
					(String)daoAuth.selectTableByEmail(txtStdId.getText()).trim(), ":");
			auth = new Auth(st.nextToken(), Integer.parseInt(st.nextToken()));
			dispose();
			new MainFrame(auth);
		}else {
			JOptionPane.showMessageDialog(null, "아이디 또는 패스워드 불일치");
			txtStdId.setText("");
			txtStdPass.setText("");
			txtStdId.requestFocus();
			return;
		}
		
	}
	protected void btnStdJoinActionPerformed(ActionEvent arg0) {
		StdInsertUpdate insert = new StdInsertUpdate(null);
		insert.setVisible(true);
	}
	private void btnProfJoinActionPerformed(ActionEvent e) {
		ProfessorInsertUpdate insert = new ProfessorInsertUpdate(null);
		insert.setVisible(true);
	}
	private void btnProfLoginActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isPasswdCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPasswd = daoAuth.selectPasswdByEmail(txtStdId.getText().trim()).toCharArray();
		if(input.length != correctPasswd.length) {
			isCorrect = false;
		}else {
			isCorrect = Arrays.equals(input, correctPasswd);
		}
		Arrays.fill(correctPasswd, '0');
		return isCorrect;
	}
	
	
	class imagePanel extends JPanel {
		Image img;
		public imagePanel(Image img) {
			this.img = img;
		}
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, this.getWidth() , this.getHeight(), this);
		}		
	}
}
