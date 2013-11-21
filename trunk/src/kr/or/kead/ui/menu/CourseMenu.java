package kr.or.kead.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.or.kead.domain.Auth;
import kr.or.kead.domain.Course;
import kr.or.kead.domain.Professor;
import kr.or.kead.service.DaoCourse;
import kr.or.kead.service.DaoProfessor;
import kr.or.kead.ui.MainFrame;
import kr.or.kead.ui.insert_update.CourseInsertUpdate;
import kr.or.kead.ui.insert_update.ProfessorInsertUpdate;
import kr.or.kead.ui.list.CourseTableList;

public class CourseMenu extends AbsMenu {
	private CourseTableList courseListView;
	private Auth auth;
	private DaoCourse daoCourse;
	private DaoProfessor daoProf;
	private Professor prof;
	private CourseInsertUpdate courseInsertUpdate;
	private int level;

	public CourseMenu(JFrame frame, int height) {
		super(frame, "강좌 관리");
		daoCourse = new DaoCourse();
		daoProf = new DaoProfessor();		
		auth = ((MainFrame)frame).getAuth();		
		level = auth.getLevel();
		if(auth.getLevel() == 3) {
			courseListView = new CourseTableList(height, null);
		}else if(auth.getLevel() == 2){
			courseListView = new CourseTableList(height, auth.getEmail());
		}		
		init();
	}

	private void init() {
		addMenu.setText("강좌 추가");
		delMenu.setText("강좌 삭제");
		updateMenu.setText("강좌 수정");
		listMenu.setText("강좌 보기");
		
	}

	@Override
	protected void addMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		if(auth.getLevel() == 2) {
			courseInsertUpdate = new CourseInsertUpdate(null, auth.getEmail());
		}else {
			courseInsertUpdate = new CourseInsertUpdate(null, null);
		}		
		courseInsertUpdate.setVisible(true);
		if (courseInsertUpdate.showDialog()==0)courseListView.setTableModel();
	}

	@Override
	protected void delMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		prof = daoProf.selectTableByEmail(auth.getEmail());
		int code = prof.getCode();
		int res;
		if(level == 3) {
			res = searchNum(daoCourse, "삭제", 0);
		}else {
			res = searchNum(daoCourse, "삭제", code);
		}
		
		if (res != -1 && daoCourse.deleteDao(res) != -1) {			
			JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
			courseListView.setTableModel();
		} else {
			JOptionPane.showMessageDialog(null, "삭제 실패.");
		}
	}

	@Override
	protected void updateMenuActionPerformed(ActionEvent e) {
		listMenuActionPerformed(e);
		prof = daoProf.selectTableByEmail(auth.getEmail());
		int code = prof.getCode();
		int res;
		if(level == 3) {
			res = searchNum(daoCourse, "수정", 0);
		}else {
			res = searchNum(daoCourse, "수정", code);
		}
		
		if(res != -1) {
			Course course = (Course)daoCourse.selectTableById(res);
			if(level == 3) {
				courseInsertUpdate = new CourseInsertUpdate(course , null);
			}else if(level ==2) {
				courseInsertUpdate = new CourseInsertUpdate(course , auth.getEmail());
			}
			
			courseInsertUpdate.setVisible(true);			
			if(courseInsertUpdate.showDialog() == 0)courseListView.setTableModel();
		}

	}

	@Override
	protected void listMenuActionPerformed(ActionEvent e) {
		refreshList(courseListView);
	}

}
