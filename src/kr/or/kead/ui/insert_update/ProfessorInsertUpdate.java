package kr.or.kead.ui.insert_update;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.or.kead.domain.Depart;
import kr.or.kead.domain.Professor;
import kr.or.kead.module.RegEmail;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoProfessor;

import javax.swing.JPasswordField;

public class ProfessorInsertUpdate extends AbsInsertUpdate {
	private JTextField txtName;
	private JTextField txtCourse;
	private JLabel lbl_profCode;
	private JComboBox<Object> comboDepart;
	private DaoDepart daoDepart;
	private Professor prof;
	private int codePlus;
	private RegEmail txtEmail;
	private JPasswordField password;
		
	
	public ProfessorInsertUpdate(Object obj) {
		super(obj, "교수 관리");		
	}

	@Override
	protected void initDao() {
		daoTable = new DaoProfessor();
		daoDepart = new DaoDepart();
	}

	@Override
	protected void updateInit() {		
		prof = (Professor)obj;
		if(prof!=null) {
			lbl_profCode.setText(Integer.toString(prof.getCode()));			
			txtName.setText(prof.getName());
			Depart depart = (Depart)daoDepart.selectTableById(prof.getDepart());
			comboDepart.setSelectedItem(depart.getCode()+":"+depart.getName());
			comboDepart.setEnabled(false);
			txtCourse.setText(prof.getCourse());
			password.setText(prof.getPass());
			txtEmail.setEmail(prof.getEmail());
		}else {
			dispose();
		}
	}

	@Override
	protected Object getObject() {
		StringTokenizer st = new StringTokenizer((String)comboDepart.getSelectedItem(), ":");
		if( obj == null) {
			prof = new Professor(Integer.parseInt(lbl_profCode.getText()), txtEmail.getEmail(),
					new String(password.getPassword()),txtName.getText(),
					Integer.parseInt(st.nextToken()), txtCourse.getText());
			return prof;
		}else {
			prof = (Professor) obj;
			prof.setCode(Integer.parseInt(lbl_profCode.getText()));
			prof.setName(txtName.getText());
			prof.setDepart(Integer.parseInt(st.nextToken()));
			prof.setCourse(txtCourse.getText());
			prof.setPass(new String(password.getPassword()));
			prof.setEmail(txtEmail.getEmail());
			return prof;
		}		
	}

	@Override
	protected boolean isValidCheck() {
		if(txtName.getText().equals("")||txtCourse.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "빈칸을 채우세요.");
			txtName.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	protected JPanel getMainPanel() {
		codePlus = 1;
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(6, 2, 5, 5));
		
		JLabel lbl_code = new JLabel("교수코드");
		lbl_code.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_code.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_code);
		
		lbl_profCode = new JLabel();		
		lbl_profCode.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_profCode.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_profCode);
		
		JLabel lbl_email = new JLabel("이 메 일");
		lbl_email.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_email.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_email);
		
		txtEmail = new RegEmail();		
		txtEmail.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(txtEmail);
		
		
		JLabel lbl_pass = new JLabel("비밀번호");
		lbl_pass.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pass.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_pass);
		
		password = new JPasswordField();
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(password);
		
		JLabel lbl_name = new JLabel("교 수 명");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_name);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lbl_depart = new JLabel("학 과 명");
		lbl_depart.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_depart.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_depart);
		
		ArrayList<String>arDeparts = daoDepart.selectTableAllList();
		Object[] obj = arDeparts.toArray();
		comboDepart = new JComboBox<Object>(obj);
		comboDepart.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		comboDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codeChange();		
			}
		});
		centerPanel.add(comboDepart);
		
		JLabel lbl_course = new JLabel("담당과목");
		lbl_course.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_course.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_course);
		
		txtCourse = new JTextField();
		txtCourse.setHorizontalAlignment(SwingConstants.CENTER);
		txtCourse.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(txtCourse);
		txtCourse.setColumns(10);
		codeChange();
		return centerPanel;
	}
	
	private void codeChange() {
		if(obj ==null) {
			StringTokenizer st = new StringTokenizer((String)comboDepart.getSelectedItem(), ":");
			int token = Integer.parseInt(st.nextToken());
			int code = ((DaoProfessor)daoTable).selectMaxCode(token);
			if(code == 0) {
				lbl_profCode.setText(Integer.toString(token*10+codePlus));
			}else {
				lbl_profCode.setText(Integer.toString(code+codePlus));
			}
		}
		
		
	}

}
