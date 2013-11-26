package kr.or.kead.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.or.kead.domain.Auth;
import kr.or.kead.domain.InfoStudent;
import kr.or.kead.domain.Professor;
import kr.or.kead.domain.RequestCourse;
import kr.or.kead.service.DaoCourse;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoProfessor;
import kr.or.kead.service.DaoRequestCourse;
import kr.or.kead.ui.MainFrame;
import kr.or.kead.ui.insert_update.ProfessorInsertUpdate;
import kr.or.kead.ui.insert_update.RequestCourseInsertUpdate;
import kr.or.kead.ui.list.AbsTableList;
import kr.or.kead.ui.list.RequestCourseTableList;

public class RequestCourseMenu extends AbsMenu {
	private AbsTableList requestCourseListVeiw;
	private Auth auth;
	private int level;
	private DaoRequestCourse daoReqCourse;
	private DaoInfoStudent daoStd;
	private DaoProfessor daoProf;
	private RequestCourse reqCourse;
	private RequestCourseInsertUpdate insUpdate;
	
	public RequestCourseMenu(JFrame frame, int height) {
		super(frame, "개설 강좌");
		auth = ((MainFrame)frame).getAuth();
		daoReqCourse = new DaoRequestCourse();
		daoStd = new DaoInfoStudent();
		daoProf = new DaoProfessor();
		level = auth.getLevel();
		if(level == 3) {
			requestCourseListVeiw = new RequestCourseTableList(height, null);
			addMenu.setEnabled(false);
		}else {
			requestCourseListVeiw = new RequestCourseTableList(height, auth);
			if(level == 2) {
				addMenu.setEnabled(false);
			}
		}
		
		
	}

	@Override
	protected void addMenuActionPerformed(ActionEvent e) {
		refreshList(requestCourseListVeiw);
		insUpdate = new RequestCourseInsertUpdate(null);
		insUpdate.setVisible(true);

	}

	@Override
	protected void delMenuActionPerformed(ActionEvent e) {
		refreshList(requestCourseListVeiw);
		System.out.println("email =" + auth.getEmail());
		int res;
		if(level == 3) {
			res = searchNum(daoReqCourse, "삭제", 0);
		}else if(level ==2) {
			// 수정 수정 수정
			Professor prof = daoProf.selectTableByEmail(auth.getEmail());
			res = searchNum(daoReqCourse, "삭제", prof.getCode());
		}else {
			InfoStudent std = daoStd.selectTableByEmail(auth.getEmail());			
			res = searchNum(daoStd, "삭제", std.getIdx());			
		}
		System.out.println("res = "+ res);
		if (res != -1 && daoReqCourse.deleteDao(res) != -1) {			
			JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
			requestCourseListVeiw.setTableModel();
		} else {
			JOptionPane.showMessageDialog(null, "삭제 실패.");
		}

	}

	@Override
	protected void updateMenuActionPerformed(ActionEvent e) {
		refreshList(requestCourseListVeiw);
		System.out.println("email =" + auth.getEmail());
		int res;
		if(level == 3) {
			res = searchNum(daoReqCourse, "수정", 0);
		}else if(level ==2) {		
			Professor prof = daoProf.selectTableByEmail(auth.getEmail());
			System.out.println("prof code " + prof.getCode());
			res = searchNum(daoProf, "수정", prof.getCode());
		}else {
			InfoStudent std = daoStd.selectTableByEmail(auth.getEmail());			
			res = searchNum(daoStd, "수정", std.getIdx());			
		}
		if(res != -1) {
			reqCourse = (RequestCourse)daoReqCourse.selectTableById(res);			
			insUpdate = new RequestCourseInsertUpdate(reqCourse);
			insUpdate.setVisible(true);
			if(insUpdate.showDialog() == 0)requestCourseListVeiw.setTableModel();
		}

	}

	@Override
	protected void listMenuActionPerformed(ActionEvent e) {
		// 강좌 리스트
		refreshList(requestCourseListVeiw);
	}

}
