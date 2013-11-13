package kr.or.kead.ui.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import kr.or.kead.domain.Auth;
import kr.or.kead.domain.InfoStudent;
import kr.or.kead.service.DaoInfoStudent;

public class PasswdChange extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField oldPasswd;
	private JPasswordField newPasswd;
	private JPasswordField confirmPasswd;
	private JButton okButton;
	private JButton cancelButton;
	private DaoInfoStudent daoStd;
	private InfoStudent std;
	private String email;

	
	public PasswdChange(String email) {	
		this.email = email;
		init();
		daoStd = new DaoInfoStudent();
		
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
		
	}
	protected void actionPerformedCancelButton(ActionEvent arg0) {
		dispose();
	}
}
