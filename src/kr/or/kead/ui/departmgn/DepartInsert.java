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
	private DaoDepart dao;
	private int departCode;
	private Depart depart;
	private MenuMgn menuMgn;
	
	public DepartInsert(MenuMgn mgn) {
		menuMgn = mgn;
		initialize();
	}
	public DepartInsert(int departCode, MenuMgn mgn) {
		menuMgn = mgn;
		this.departCode = departCode;
		initialize();
		fillValues(departCode);
	}
	
	private void initialize() {
		dao = new DaoDepart();
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
		dao = new DaoDepart();
		depart = (Depart)dao.selectTableById(departCode);
		txtDepart.setText(depart.getName());
		txtTel.setPhone(depart.getTel());
		btnSave.setText("수정");		
	}
	

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCancel) {
			btnCancelActionPerformed(arg0);
		}
		if (arg0.getSource() == btnSave) {
			if(btnSave.getText().equals("저장")) {
				btnSaveActionPerformed(arg0);
			}else {
				btnUpdateActionPerformed(arg0);
			}
		}
	}
	private void btnUpdateActionPerformed(ActionEvent e) {
		if(isFieldCheck()) {
			depart = (Depart)dao.selectTableById(departCode);
			depart.setName(txtDepart.getText());
			depart.setTel(txtTel.getPhone());
			if(dao.updateDao(depart) == -1) {
				JOptionPane.showMessageDialog(null, "수정에 실패 하였습니다.");
				System.out.println("2");
			}else {
				menuMgn.refreshList(new DepartList(new CustomDepartTableModel(), menuMgn));
				dispose();
			}
		}
	}
	protected void btnSaveActionPerformed(ActionEvent e) {
		if(isFieldCheck()) {			
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
