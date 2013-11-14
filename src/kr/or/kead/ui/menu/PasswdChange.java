package kr.or.kead.ui.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import kr.or.kead.domain.Auth;
import kr.or.kead.domain.InfoStudent;
import kr.or.kead.domain.Professor;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoProfessor;
import kr.or.kead.service.DaoTable;

public class PasswdChange extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField oldPasswd;
	private JPasswordField newPasswd;
	private JPasswordField confirmPasswd;
	private JButton okButton;
	private JButton cancelButton;	
	private DaoTable dao;	
	private Professor prof;
	private String passwd;
	private int level;
	private String email;

	
	public PasswdChange(Auth auth) {		
		init();
		level = auth.getLevel();
		email = auth.getEmail();
		if(level == 1) {
			dao = new DaoInfoStudent();
			passwd = dao.selectPasswdByEmail(auth.getEmail());	
		}else if(level == 2) {
			dao = new DaoProfessor();
			passwd = dao.selectPasswdByEmail(auth.getEmail());
		}		
	}

	private void init() {
		setFont(new Font("맑은 고딕", Font.BOLD, 17));
		setTitle("비밀번호 변경");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lbl_oldPass = new JLabel("기존 비밀번호");
			lbl_oldPass.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			contentPanel.add(lbl_oldPass);
		}
		{
			oldPasswd = new JPasswordField();
			oldPasswd.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			oldPasswd.setColumns(20);
			contentPanel.add(oldPasswd);
		}
		{
			JLabel lbl_newPass = new JLabel("새 비밀번호   ");
			lbl_newPass.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			contentPanel.add(lbl_newPass);
		}
		{
			newPasswd = new JPasswordField();
			newPasswd.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			newPasswd.setColumns(20);
			contentPanel.add(newPasswd);
		}
		{
			JLabel lbl_passConfirm = new JLabel("비밀번호 확인");
			lbl_passConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			contentPanel.add(lbl_passConfirm);
		}
		{
			confirmPasswd = new JPasswordField();
			confirmPasswd.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			confirmPasswd.setColumns(20);
			contentPanel.add(confirmPasswd);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cancelButton) {
			actionPerformedCancelButton(arg0);
		}
		if (arg0.getSource() == okButton) {
			actionPerformedOkButton(arg0);
		}
	}
	protected void actionPerformedOkButton(ActionEvent arg0) {
		if(Arrays.equals(oldPasswd.getPassword() , passwd.toCharArray())){
			if(Arrays.equals(newPasswd.getPassword(), confirmPasswd.getPassword())) {
				if(dao.updatePasswdByEmail(new String(confirmPasswd.getPassword()), email) > -1){
					JOptionPane.showMessageDialog(null, "비밀번호가 변경 되었습니다.");
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "비밀번호가 변경실패.");
				}
			}else {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치 하지 않습니다.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "기존의 비밀번호와 일치 하지 않습니다.");
		}		
	}
	protected void actionPerformedCancelButton(ActionEvent arg0) {
		dispose();
	}	
}
