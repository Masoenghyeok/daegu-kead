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

import kr.or.kead.domain.Course;
import kr.or.kead.domain.Professor;
import kr.or.kead.service.DaoCourse;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoProfessor;

public class CourseInsertUpdate extends AbsInsertUpdate {
	private JLabel lblNewLabel;
	private JLabel lbl_courseCode;
	private JLabel lblNewLabel_2;
	private JComboBox<String> combo_depart;
	private JTextField txt_courseName;
	private JLabel lblNewLabel_3;
	private JTextField txt_material;
	private JLabel lblNewLabel_4;
	private JComboBox<String> combo_prof;
	private JLabel lblNewLabel_5;
	private DaoCourse daoCourse;
	private DaoDepart daoDepart;
	private DaoProfessor daoProf;
	private Course course;
	private String email;
	public CourseInsertUpdate(Object obj, String email) {		
		super(obj, "강좌 정보");
		this.email = email;
	}

	@Override
	protected void initDao() {		
		daoTable = new DaoCourse();
		daoCourse = new DaoCourse();
		daoDepart = new DaoDepart();
		daoProf = new DaoProfessor();
	}

	@Override
	protected void updateInit() {
		course = (Course)obj;
		lbl_courseCode.setText(Integer.toString(course.getCode()));
		combo_depart.setSelectedItem(daoDepart.selectNameByCode(course.getDepart_code()));
		txt_courseName.setText(course.getSubject());
		txt_material.setText(course.getMaterial());
		combo_prof.setSelectedItem(daoProf.selectCodeNameById(course.getProf_code()));
	}

	@Override
	protected Object getObject() {	
		
		if(obj == null) {			
			course = new Course(Integer.parseInt(lbl_courseCode.getText()),
					stringTokenComboItem(combo_depart.getSelectedItem()),
					txt_courseName.getText(), txt_material.getText(),
					stringTokenComboItem(combo_prof.getSelectedItem()));
			
			return course;
		}else {
			course = (Course)obj;
			course.setCode(Integer.parseInt(lbl_courseCode.getText()));
			course.setDepart_code(stringTokenComboItem(combo_depart.getSelectedItem()));
			course.setSubject(txt_courseName.getText());
			course.setMaterial(txt_material.getText());
			course.setProf_code(stringTokenComboItem(combo_prof.getSelectedItem()));
		
			return course;
		}	
	}
	
	

	private void comboBoxSelect() {
		System.out.println("comboBoxSelect");
		System.out.println("email = " + email);
		if(email != null) {
			System.out.println("email in");
			Professor prof = daoProf.selectTableByEmail(email);
			combo_prof.setSelectedItem(prof.getCode() + ":" + prof.getName());
			combo_prof.setEditable(false);
		}		
	}

	private int stringTokenComboItem(Object comboValue) {
		StringTokenizer st = new StringTokenizer((String)comboValue, ":");
		return Integer.parseInt(st.nextToken());		
	}

	@Override
	protected boolean isValidCheck() {		
		if(txt_courseName.getText().equals("")||
				lbl_courseCode.getText().equals("")||txt_material.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "필수 항목이 빠져 있습니다.");
			return false;
		}
		return true;
	}

	@Override
	protected JPanel getMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5, 2, 5, 5));
		
		lblNewLabel = new JLabel("강좌 코드");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel);
		
		lbl_courseCode = new JLabel("");
		lbl_courseCode.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_courseCode.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lbl_courseCode);
		
		lblNewLabel_2 = new JLabel("학       과");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel_2);
		
		ArrayList<Object> departs = daoDepart.selectNames();
		Object[] obj = departs.toArray();
		
		combo_depart = new JComboBox(obj);
		combo_depart.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		combo_depart.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				codeChange();							
			}
		});
		mainPanel.add(combo_depart);
		
		lblNewLabel_3 = new JLabel("강  좌  명");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel_3);
		
		txt_courseName = new JTextField();
		txt_courseName.setHorizontalAlignment(SwingConstants.CENTER);
		txt_courseName.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mainPanel.add(txt_courseName);
		txt_courseName.setColumns(40);
		
		lblNewLabel_4 = new JLabel("교  재  명");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel_4);
		
		txt_material = new JTextField();
		txt_material.setHorizontalAlignment(SwingConstants.CENTER);
		txt_material.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		mainPanel.add(txt_material);
		txt_material.setColumns(40);
		
		lblNewLabel_5 = new JLabel("교  수  명");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel_5);
		
		
		ArrayList<String>profs =  daoProf.selectTableAllList();
		Object[] strs = profs.toArray();				
		combo_prof = new JComboBox(strs);		
		combo_prof.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(combo_prof);
		
		
		codeChange();
		comboBoxSelect();
		return mainPanel;
	}
	
	private void codeChange() {
		if(obj ==null) {
			StringTokenizer st = new StringTokenizer((String)combo_depart.getSelectedItem(), ":");
			int token = Integer.parseInt(st.nextToken());
			int code = daoCourse.selectMaxCode(token);
			if(code == 0) {
				lbl_courseCode.setText(token + "001");
			}else {
				lbl_courseCode.setText(Integer.toString(code+1));
			}
		}		
	}

}
