package kr.or.kead.ui.insert_update;

import java.awt.Font;
import java.awt.GridLayout;
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
import kr.or.kead.module.PhoneCheck;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoProfessor;
import kr.or.kead.service.DaoTable;

@SuppressWarnings("serial")
public class DepartInsertUpdate extends AbsInsertUpdate {	
	private JTextField jtf_departName;
	private JComboBox<Object> comboProf;
	private PhoneCheck txtTel;
	private JLabel lbl_departCode;	
	private Depart depart;
	private DaoProfessor daoprofessor;
		
	
	public DepartInsertUpdate(Object obj) {		
		super(obj, "학과 정보");		
	}

	@Override
	protected void initDao() {
		daoTable = new DaoDepart();
		daoprofessor = new DaoProfessor();
	}

	@Override
	protected void updateInit() {
		depart = (Depart)obj;
		if(depart.getCode() !=0) {
			lbl_departCode.setText(Integer.toString(depart.getCode()));
			jtf_departName.setText(depart.getName());
			Object prof = daoprofessor.selectCodeNameById(depart.getProf());
			comboProf.setSelectedItem(prof);   // 추후 수정
			txtTel.setPhone(depart.getTel());
		}else {
			dispose();
		}
	}

	@Override
	protected Object getObject() {
		String prof = (String)comboProf.getSelectedItem();
		StringTokenizer st = new StringTokenizer(prof, ":");
		if(obj == null) {				// 추가			
			depart = new Depart(Integer.parseInt(lbl_departCode.getText()), jtf_departName.getText(),
					Integer.parseInt(st.nextToken().trim()), txtTel.getPhone());
			return depart;
		}else {							// 수정
			depart = (Depart) obj;
			depart.setCode(Integer.parseInt(lbl_departCode.getText()));
			depart.setName(jtf_departName.getText());			
			depart.setProf(Integer.parseInt(st.nextToken().trim()));
			depart.setTel(txtTel.getPhone());
			return depart;
		}		
	}

	@Override
	protected boolean isValidCheck() {		
		if(jtf_departName.getText().equals("") || txtTel.getPhone().equals("")) {
			JOptionPane.showMessageDialog(null, "빈칸을 채우세요.");
			jtf_departName.requestFocus();
			return false;
		}		
		return true;
	}

	@Override
	protected JPanel getMainPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4, 2, 5,5 ));	
		
		JLabel lbl_code = new JLabel("학과코드");
		lbl_code.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_code.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_code);		
		
		lbl_departCode = new JLabel(String.valueOf(((DaoDepart)daoTable).selectMaxCode()+100));
		lbl_departCode.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lbl_departCode.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lbl_departCode);
		
		JLabel lbl_name = new JLabel("학  과  명");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_name);
	
		jtf_departName = new JTextField();
		jtf_departName.setHorizontalAlignment(SwingConstants.CENTER);
		jtf_departName.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(jtf_departName);
		jtf_departName.setColumns(10);
		
		JLabel lbl_prof = new JLabel("담당교수");
		lbl_prof.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_prof.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_prof);

		ArrayList<String> arList = daoprofessor.selectTableAllList();	// 번호:이름
		Object[] objList = arList.toArray();				
		comboProf = new JComboBox<>(objList);		
		centerPanel.add(comboProf);

	
		JLabel lbl_tel = new JLabel("전화번호");
		lbl_tel.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(lbl_tel);
	
		txtTel = new PhoneCheck(false);
		txtTel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		centerPanel.add(txtTel);
	
		return centerPanel;
	}

}
