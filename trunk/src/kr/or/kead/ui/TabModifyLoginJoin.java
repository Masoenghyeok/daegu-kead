package kr.or.kead.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import kr.or.kead.ui.list.ProfessorTableList;

public class TabModifyLoginJoin extends JFrame implements ActionListener {
	private JPanel loginPanel;
	private JPanel btnPanel;
	private JButton btnLogin;
	private JButton btnJoin;	
	private JLabel lblStdId;
	private JTextField txt_Id;
	private JLabel lbl_pass;
	private JPasswordField txt_pass;
	private JTabbedPane tabMenu;
	private JPanel stdPanel;
	private JPanel profPanel;
	
	private Image img;
	private ImageIcon icon;
	private JPanel stdIdPanel;
	private JPanel stdPassPanel;
	
	private DaoAuth daoAuth;
	private Auth auth;
	
	public TabModifyLoginJoin() {
		initialize();
		daoAuth = new DaoAuth();
	}
	private void initialize() {
		setLayout(new BorderLayout(10, 10));
		setBounds(0, 0, 300, 250);
		
		tabMenu = new JTabbedPane(JTabbedPane.BOTTOM);		
		tabMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 16));		
		add(tabMenu, BorderLayout.CENTER);
		
				
		img = Toolkit.getDefaultToolkit().getImage("kead3.jpg");
		stdPanel = new imagePanel(img);
		panelMake("학생", stdPanel);
		
		img = Toolkit.getDefaultToolkit().getImage("kead4.jpg");
		profPanel = new imagePanel(img);
		panelMake("교수", profPanel);
			
//		tabMenu.addTab("교수", null, stdPanel, null);
//		img = Toolkit.getDefaultToolkit().getImage("kead4.jpg");	
//		panelMake("교수");
//		tabMenu.addTab("교수", null, stdPanel, null);
		
		
		tabMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tabMenu.getSelectedIndex() == 1) {
					btnJoin.setText("교수");
				}else {
					btnJoin.setText("학생");
				}
			}

		});
		
		
		setVisible(true);
	
	}
	
	
	private void panelMake(String compareUser, JPanel panel) {		
		panel.setLayout(new BorderLayout(0, 0));
		tabMenu.addTab(compareUser, null, panel, null);
		
		Color color = new Color(00, 33, 33, 33);
		
		loginPanel = new JPanel();		
		panel.add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(new GridLayout(2, 1, 10, 10));		
		loginPanel.setBackground(color);
		
		stdIdPanel = new JPanel();
		stdIdPanel.setBackground(color);
		loginPanel.add(stdIdPanel);
		
		lblStdId = new JLabel("아이디");		
		stdIdPanel.add(lblStdId);
		lblStdId.setForeground(Color.GREEN);
		lblStdId.setHorizontalAlignment(SwingConstants.CENTER);
		lblStdId.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		
		txt_Id = new JTextField();
		stdIdPanel.add(txt_Id);
		txt_Id.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Id.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		txt_Id.setColumns(10);
		
		stdPassPanel = new JPanel();
		loginPanel.add(stdPassPanel);
		stdPassPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		stdPassPanel.setBackground(color);
		
		lbl_pass = new JLabel("패스워드");
		stdPassPanel.add(lbl_pass);
		lbl_pass.setForeground(Color.GREEN);
		lbl_pass.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pass.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		
		txt_pass = new JPasswordField();
		txt_pass.setColumns(10);
		stdPassPanel.add(txt_pass);
		txt_pass.setHorizontalAlignment(SwingConstants.CENTER);
		txt_pass.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		
						
		btnPanel = new JPanel();		
		panel.add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setBackground(color);
		
		btnLogin = new JButton("로그인");
		btnLogin.setHorizontalAlignment(SwingConstants.CENTER);
		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		btnLogin.addActionListener(this);
		btnPanel.add(btnLogin);
		
		btnJoin = new JButton(compareUser+"가입");
		btnJoin.setHorizontalAlignment(SwingConstants.CENTER);
		btnJoin.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		btnJoin.addActionListener(this);
		btnPanel.add(btnJoin);
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == btnJoin) {	
			System.out.println("학생");
			btnStdJoinActionPerformed(e);
		}else if (e.getSource() == btnLogin) {
			btnLoginActionPerformed(e);
		}
	}
	
	protected void btnLoginActionPerformed(ActionEvent e) {	
		if (isPasswdCorrect(txt_pass.getPassword())) {
			StringTokenizer st = new StringTokenizer(
					(String)daoAuth.selectTableByEmail(txt_Id.getText()).trim(), ":");
			auth = new Auth(st.nextToken(), Integer.parseInt(st.nextToken()));
			dispose();
			new MainFrame(auth);
		}else {
			JOptionPane.showMessageDialog(null, "아이디 또는 패스워드 불일치");
			txt_Id.setText("");
			txt_pass.setText("");
			txt_Id.requestFocus();
			return;
		}
	}
	protected void btnStdJoinActionPerformed(ActionEvent e) {		
			StdInsertUpdate insert = new StdInsertUpdate(null);
			insert.setVisible(true);		
	}
	
	
	private boolean isPasswdCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPasswd = daoAuth.selectPasswdByEmail(txt_Id.getText().trim()).toCharArray();
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
