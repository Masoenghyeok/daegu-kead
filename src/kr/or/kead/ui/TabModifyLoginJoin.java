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
	private JPanel stdLoginPanel;
	private JPanel stdBtnPanel;
	private JButton btnLogin;
	private JButton btnJoin;
	private JButton btnprof;
	private JLabel lblStdId;
	private JTextField txtStdId;
	private JLabel lblStdPass;
	private JPasswordField txtStdPass;
	private JTabbedPane tabMenu;
	private JPanel stdPanel;	
	
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
		
		
		
		
		img = Toolkit.getDefaultToolkit().getImage("kead.jpg");	
		panelMake("학생");
		tabMenu.addTab("학생", null, stdPanel, null);
		img = Toolkit.getDefaultToolkit().getImage("kead2.jpg");	
		panelMake("교수");
		tabMenu.addTab("교수", null, stdPanel, null);
		
		
		tabMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tabMenu.getSelectedIndex() == 1) {
					btnJoin.setVisible(false);
					btnprof.setVisible(true);
				}
			}

		});
		
		
		setVisible(true);
	
	}
	
	private void panelMake(String compareUser) {					
		stdPanel = new imagePanel(img);	
		
		
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
				
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(this);
		btnLogin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));		
		stdBtnPanel.add(btnLogin);
				
		btnJoin = new JButton("학생가입");
		btnJoin.addActionListener(this);
		btnJoin.setFont(new Font("맑은 고딕", Font.PLAIN, 16));		
		stdBtnPanel.add(btnJoin);	
		
		btnprof = new JButton("교수가입");
		btnprof.addActionListener(this);
		btnprof.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnprof.setVisible(false);
		stdBtnPanel.add(btnprof);	
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == btnJoin) {	
			System.out.println("학생");
			btnStdJoinActionPerformed(e);
		}else if (e.getSource() == btnLogin) {
			btnLoginActionPerformed(e);
		}else if (e.getSource() == btnprof) {
			System.out.println("in");
		}
	}
	
	protected void btnLoginActionPerformed(ActionEvent e) {	
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
	protected void btnStdJoinActionPerformed(ActionEvent e) {		
			StdInsertUpdate insert = new StdInsertUpdate(null);
			insert.setVisible(true);		
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
