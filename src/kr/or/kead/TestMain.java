package kr.or.kead;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import kr.or.kead.domain.Depart;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoTable;

public class TestMain {

	public static void main(String[] args) {
//		new MainFrame();
//		CreateInsertPostAddr cip = new CreateInsertPostAddr();
//		cip.makeInsertSql();
//		StdAddr run = new StdAddr();
//		run.setVisible(true);
		
//		if( dao.insertDao(new Depart(500, "경영", 0, "053-1234-1234")) <0 ) {
//			JOptionPane.showMessageDialog(null, "에러");
//		}else {
//			JOptionPane.showMessageDialog(null, "성공");
//		}
		
		DaoDepart dao = new DaoDepart();
		ArrayList<Object> departs = dao.selectDao();
		for(Object depart:departs) {
			System.out.println(depart);
		}
		
		
		
		int res = 0;
		
		res = dao.insertDao(new Depart(500, "경영회계", 0, "053-1234-1234"));
		isCheckt(res);
		res = dao.updateDao(new Depart(200,"전자정보", 0, "053-999-9999"));
		isCheckt(res);
		Depart depart = (Depart) dao.selectTableById(500);
		System.out.println(depart);
		
		res = dao.deleteDao(500);
		isCheckt(res);
		
		res = dao.selectCodeDepartByName("정보");
		System.out.println(res);
		res = dao.selectMaxCode();
		System.out.println(res);
		
	}
	
	private static void isCheckt(int res) {
		String msg = null;
		if(res == 0) {
			msg = "성공";			
		}else {
			msg = "실패";			
		}
		JOptionPane.showMessageDialog(null, msg);
	}
}
