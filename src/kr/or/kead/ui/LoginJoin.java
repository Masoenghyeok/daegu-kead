package kr.or.kead.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.or.kead.domain.Auth;
import kr.or.kead.service.DaoAuth;
import kr.or.kead.ui.insert_update.ProfessorInsertUpdate;
import kr.or.kead.ui.insert_update.StdInsertUpdate;

public class LoginJoin extends JFrame implements ActionListener {
	private JPanel title_panel;
	private JPanel email_panel;
	private JPanel passwd_panel;
	private JPanel btn_panel;
	private JLabel lblNewLabel;
	private JTextField txt_email;
	private JLabel lblNewLabel_1;
	private JButton btn_login;
	private JLabel lbl_title;
	private JPanel center_panel;
	private DaoAuth daoAuth;
	private Auth auth;
	private JPasswordField txt_passwd;
	private JLabel lbl_join;
	private JLabel lbl_search_passwd;
	
	public LoginJoin() {
		initialize();
		daoAuth = new DaoAuth();		
	}
	private void initialize() {
		setTitle("학사관리 시스템");
		setBounds(100, 100, 500, 300);
		Color color = new Color(0, 0, 0, 0);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		title_panel = new JPanel();
		getContentPane().add(title_panel, BorderLayout.NORTH);
		
		lbl_title = new JLabel("대구직업능력 개발원 학사 관리");
		lbl_title.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		title_panel.add(lbl_title);
		
		lbl_join = new JLabel("회원가입");
		lbl_join.setForeground(Color.BLUE);
		lbl_join.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lbl_join.addMouseListener(new mouseAction());
		title_panel.add(lbl_join);
		
		lbl_search_passwd = new JLabel("비밀번호 찾기");
		lbl_search_passwd.setForeground(Color.RED);
		lbl_search_passwd.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lbl_search_passwd.addMouseListener(new mouseAction());
		title_panel.add(lbl_search_passwd);
		
		center_panel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				System.out.println("tetet");
				Image img = Toolkit.getDefaultToolkit().getImage("kead4.jpg");
				g.drawImage(img, 0, 0, this.getWidth() , this.getHeight(), this);
			}			
		};
		getContentPane().add(center_panel, BorderLayout.CENTER);
		
		email_panel = new JPanel();
		center_panel.add(email_panel);
		email_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		email_panel.setBackground(color);
		
		lblNewLabel = new JLabel("이  메  일");
		lblNewLabel.setForeground(new Color(102, 153, 51));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		email_panel.add(lblNewLabel);
		
		txt_email = new JTextField();
		txt_email.setHorizontalAlignment(SwingConstants.CENTER);
		txt_email.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		email_panel.add(txt_email);
		txt_email.setColumns(10);
		
		passwd_panel = new JPanel();
		center_panel.add(passwd_panel);
		passwd_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		passwd_panel.setBackground(color);
		
		lblNewLabel_1 = new JLabel("비밀번호 ");
		lblNewLabel_1.setForeground(new Color(102, 153, 51));
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
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
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
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
	
	class mouseAction extends MouseAdapter {		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == lbl_join) {
				clickedJoin();
			}else {
				String searchPass = JOptionPane.showInputDialog(null, "이메일을 입력하세요");
				if(checkEmail(searchPass)) {
					String message = daoAuth.selectPasswdByEmail(searchPass);
					if(message !=null) {
						JOptionPane.showMessageDialog(null, searchPass + "님의 비밀번호는 \n" +message);
					}else {
						JOptionPane.showMessageDialog(null, searchPass + "에 해당하는 아이디가 없습니다.");
					}
				}else {
					JOptionPane.showMessageDialog(null, searchPass + "는 형식이 잘못되었습니다.");
				}
			}			
			
		}
		private void clickedJoin() {
			Object[] obj = {"학생","교수","교직원"};
			String str = (String)JOptionPane.showInputDialog(null, "가입대상을 선택하세요", "회원가입", JOptionPane.OK_CANCEL_OPTION,
					null, obj, 0);
			if(str == obj[0]) {
				StdInsertUpdate stdInsert = new StdInsertUpdate(null);
				stdInsert.setVisible(true);
			}else if(str == obj[1]) {
				ProfessorInsertUpdate profInsert = new ProfessorInsertUpdate(null);
				profInsert.setVisible(true);
			}else {
				
			}			
		}
		@Override
		public void mouseEntered(MouseEvent e) {		
			setCursor(Cursor.HAND_CURSOR);
		}
		@Override
		public void mouseExited(MouseEvent e) {			
			setCursor(Cursor.DEFAULT_CURSOR);
		}		
	}
	
	public boolean checkEmail(String str) {
		Pattern pattern = Pattern.compile("(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+");
		Matcher match = pattern.matcher(str);
		if (!match.find()) {				
			JOptionPane.showMessageDialog(null, "메일 형식이 잘못되었습니다.");
			return false;
		}
		return true;
		
	}
}
