package kr.or.kead;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.ui.MainFrame;

public class TestMain {

	public static void main(String[] args) {
		new MainFrame();
		/*
		InfoStudent std01 = new InfoStudent();
		InfoStudent std02 = new InfoStudent(1, "김태희", "123456-1234567", "2013/10/01", "2013/11/01", "010-1234-5678",
				"053-1234-5678", "대구 달서구", 200, 0, "test@test.co.kr");
		InfoStudent std03 = new InfoStudent("송혜교", "123456-1234567", "2013/11/01", 200, 1);
		System.out.println(std01);
		System.out.println(std02);
		System.out.println(std03);
		
		InfoStudent std02 = new InfoStudent("공효진", "123456-1234567", "2013/10/01", "2013/11/01",
				"010-1234-5678","053-1234-5678", "대구 달서구", 200, 0, 1, "test@test.co.kr");
		
		DaoInfoStudent dis = new DaoInfoStudent();
		// dis.insertDao(std02);
		std02.setIdx(6);
		std02.setStdName("효린");
		std02.setEmail("kead@kead.or.kr");
		System.out.println(std02);
				
		dis.updateDao(std02);
		*/
		
	}
}
