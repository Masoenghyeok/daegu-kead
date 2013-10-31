package kr.or.kead.ui.departmgn;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.or.kead.domain.Depart;
import kr.or.kead.module.PhoneCheck;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoTable;
import kr.or.kead.ui.menu.MenuMgn;

public class DepartInsert extends JDialog implements ActionListener {
	private JTextField txtDepart;
	private PhoneCheck txtTel;
	private JButton btnSave;
	private JButton btnCancel;
	
	public DepartInsert(MenuMgn mgn) {
		initialize();
	}
	public DepartInsert(int departCode, MenuMgn mgn) {
		initialize();
		fillValues(departCode);
	}
	
	private void initialize() {
		setBounds(100, 100, 450, 150);
		getContentPane().setLayout(new GridLayout(3, 2, 5, 5));
		{
			JLabel lblNewLabel = new JLabel("학      과");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			getContentPane().add(lblNewLabel);
		}
		{
			txtDepart = new JTextField();
			txtDepart.setHorizontalAlignment(SwingConstants.CENTER);
			txtDepart.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			getContentPane().add(txtDepart);
			txtDepart.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("전화번호");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			getContentPane().add(lblNewLabel_2);
		}
		{
			txtTel = new PhoneCheck(false);
			txtTel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			getContentPane().add(txtTel);
			
		}
		{
			btnSave = new JButton("저장");
			btnSave.addActionListener(this);
			btnSave.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			getContentPane().add(btnSave);
		}
		{
			btnCancel = new JButton("취소");
			btnCancel.addActionListener(this);
			btnCancel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
			getContentPane().add(btnCancel);
		}
	}
	
	private void fillValues(int departCode) {
		DaoDepart dao = new DaoDepart();
		Depart departs = (Depart)dao.selectTableById(departCode);
		txtDepart.setText(departs.getName());
		txtTel.setPhone(departs.getTel());
		btnSave.setName("수정");		
	}
	

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCancel) {
			btnCancelActionPerformed(arg0);
		}
		if (arg0.getSource() == btnSave) {
			btnSaveActionPerformed(arg0);
		}
	}
	protected void btnSaveActionPerformed(ActionEvent e) {
		if(isFieldCheck()) {
			DaoDepart dao = new DaoDepart();
			int maxCode = dao.selectMaxCode() + 100;
			Depart depart = new Depart(maxCode, txtDepart.getText(), 0, txtTel.getPhone());
			System.out.println(depart);
			if(dao.insertDao(depart) == 0) {
				JOptionPane.showMessageDialog(null, "저장이 되었습니다.");
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "저장에 실패 하였습니다.");
			}			
		}
	}
	
	protected void btnCancelActionPerformed(ActionEvent e) {
	}
	
	private boolean isFieldCheck() {
		boolean compare = true;
		if(txtDepart.getText().equals("") || txtTel.getPhone().equals("")) {
			compare = false;
		}		
		return compare;	
		
	}
	
	
}
