package kr.or.kead.ui.insert_update;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.or.kead.domain.Auth;
import kr.or.kead.domain.RequestCourse;
import kr.or.kead.service.DaoCourse;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoProfessor;
import kr.or.kead.ui.menu.MenuMgn;

public class RequestCourseInsertUpdate extends AbsInsertUpdate {
	private JLabel lblNewLabel;
	private JComboBox<String> combo_depart;
	private JLabel lblNewLabel_1;
	private JComboBox<Object> combo_prof;
	private JLabel lblNewLabel_2;
	private JComboBox<Object> combo_course;
	
	private DaoDepart daoDepart;
	private DaoCourse daoCourse;
	private DaoProfessor daoProf;
	private RequestCourse reqCourse;
	private Auth auth;

	public RequestCourseInsertUpdate(Object obj) {
		super(obj, "강좌 정보");
		auth = MenuMgn.getAuth();
	}

	@Override
	protected void initDao() {
		daoDepart = new DaoDepart();
		daoCourse = new DaoCourse();
		daoProf = new DaoProfessor();
		
	}

	@Override
	protected void updateInit() {
		System.out.println(auth.getEmail());
		reqCourse = (RequestCourse)obj;
		

	}

	@Override
	protected Object getObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isValidCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected JPanel getMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 2, 5, 5));
		
		lblNewLabel = new JLabel("학과");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel);
		
		ArrayList<Object>departs = daoDepart.selectNames();
		departs.add(0, "학과선택");
		Object[] obj = departs.toArray();		
		combo_depart = new JComboBox(obj);
		combo_depart.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		combo_depart.addActionListener(this);		
		mainPanel.add(combo_depart);
		
		lblNewLabel_1 = new JLabel("교수명");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel_1);
		
		combo_prof = new JComboBox();
		combo_prof.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		combo_prof.addActionListener(this);
		mainPanel.add(combo_prof);
		
		lblNewLabel_2 = new JLabel("개설강좌");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		mainPanel.add(lblNewLabel_2);
		
		combo_course = new JComboBox();
		combo_course.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		combo_course.addActionListener(this);
		mainPanel.add(combo_course);
		return mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
