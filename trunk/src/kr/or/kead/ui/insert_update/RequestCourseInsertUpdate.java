package kr.or.kead.ui.insert_update;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.or.kead.domain.Auth;
import kr.or.kead.domain.Course;
import kr.or.kead.domain.Depart;
import kr.or.kead.domain.InfoStudent;
import kr.or.kead.domain.Professor;
import kr.or.kead.domain.RequestCourse;
import kr.or.kead.service.DaoCourse;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoProfessor;
import kr.or.kead.service.DaoRequestCourse;
import kr.or.kead.ui.menu.MenuMgn;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RequestCourseInsertUpdate extends AbsInsertUpdate implements ItemListener {
	private JLabel lblNewLabel;
	private JComboBox<String> combo_depart;
	private JLabel lblNewLabel_1;
	private JComboBox<Object> combo_prof;
	private JLabel lblNewLabel_2;
	private JComboBox<Object> combo_course;
	
	private DaoDepart daoDepart;
	private DaoCourse daoCourse;
	private DaoRequestCourse daoReqCourse;
	private DaoProfessor daoProf;
	private RequestCourse reqCourse;
	private InfoStudent std;
	private DaoInfoStudent daoStd;
	private Auth auth;
	private JLabel lblNewLabel_3;
	private JTextField txt_grade;
	

	public RequestCourseInsertUpdate(Object obj) {
		super(obj, "강좌 정보");
		
	}

	@Override
	protected void initDao() {
		auth = MenuMgn.getAuth();
		daoTable = new DaoRequestCourse();
		daoDepart = new DaoDepart();
		daoCourse = new DaoCourse();
		daoProf = new DaoProfessor();
		daoStd = new DaoInfoStudent();
		daoReqCourse = new DaoRequestCourse();
	}

	@Override
	protected void updateInit() {		
		reqCourse = (RequestCourse)obj;
		Course course = (Course)daoCourse.selectTableById(reqCourse.getCourseCode());						
		String depart = daoDepart.selectNameByCode(course.getDepart_code());
		System.out.println("depart = " +depart);
		combo_depart.setSelectedItem(depart);
		combo_prof.setSelectedItem(daoProf.selectCodeNameById(course.getProf_code()));		
		combo_course.setSelectedItem(daoCourse.selectTableAllListByCode(course.getCode()));
		txt_grade.setText(String.valueOf(reqCourse.getGrade()));
		
		if(auth.getLevel() == 2) {
			txt_grade.setEditable(true);
		}
		
		combo_depart.updateUI();
		combo_prof.updateUI();
		combo_course.updateUI();
	}

	@Override
	protected Object getObject() {
		System.out.println("auth " + auth);
		std = daoStd.selectTableByEmail(auth.getEmail());
		StringTokenizer st = new StringTokenizer((String)combo_course.getSelectedItem(), ":");
		if(obj == null) {			
			reqCourse = new RequestCourse(std.getIdx(),Integer.parseInt(st.nextToken()));
			System.out.println( "reqCourse = " + reqCourse);
			return reqCourse;			
		}else {			
			reqCourse.setGrade(Integer.parseInt(txt_grade.getText()));
			reqCourse.setCourseCode(Integer.parseInt(st.nextToken()));
			return reqCourse;
		}		
	}

	@Override
	protected boolean isValidCheck() {
		if(combo_course.getSelectedItem().equals("")){
			return false;
		}
		return true;
	}

	@Override
	protected JPanel getMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(4, 2, 5, 5));
		
		lblNewLabel = new JLabel("학      과");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel);
		
		ArrayList<Object>departs = daoDepart.selectNames();
		departs.add(0, "학과선택");
		Object[] obj = departs.toArray();		
		combo_depart = new JComboBox(obj);
		combo_depart.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		combo_depart.addItemListener(this);		
		mainPanel.add(combo_depart);
		
		lblNewLabel_1 = new JLabel("교  수 명");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel_1);
		
		combo_prof = new JComboBox();
		combo_prof.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		combo_prof.addItemListener(this);
		mainPanel.add(combo_prof);
		
		lblNewLabel_2 = new JLabel("개설강좌");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel_2);
		
		combo_course = new JComboBox();
		combo_course.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		combo_course.addItemListener(this);
		mainPanel.add(combo_course);
		
		lblNewLabel_3 = new JLabel("점      수");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel_3);
		
		txt_grade = new JTextField();
		txt_grade.setHorizontalAlignment(SwingConstants.CENTER);
		txt_grade.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(txt_grade);
		txt_grade.setColumns(10);
		txt_grade.setEditable(false);
		return mainPanel;
	}
	
	
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == combo_depart) {
			StringTokenizer st = new StringTokenizer((String)combo_depart.getSelectedItem(), ":");		
			ArrayList<String>profs = daoProf.selectDepartCodeByName(Integer.parseInt(st.nextToken()));
			int len = profs.size();
			combo_prof.removeAllItems();
			for(int i=0;i<len;i++) {
				System.out.println(profs.get(i));
				combo_prof.addItem(profs.get(i));
			}
			combo_prof.updateUI();			
		}else if(e.getSource() == combo_prof) {
			if(combo_prof.getItemCount() > 0 ) {
				StringTokenizer st = new StringTokenizer((String)combo_prof.getSelectedItem(), ":");		
				ArrayList<String>courses = daoCourse.selectTableAllListByCode(Integer.parseInt(st.nextToken()));
				int len = courses.size();
				combo_course.removeAllItems();
				for(int i=0;i<len;i++) {
					combo_course.addItem(courses.get(i));
				}
			}				
		}		
	}
}
