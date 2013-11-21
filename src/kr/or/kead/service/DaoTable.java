package kr.or.kead.service;

import java.util.ArrayList;

import kr.or.kead.domain.InfoStudent;

public interface DaoTable {
	int insertDao(Object obj);
	int updateDao(Object obj);
	int deleteDao(int regNo);
	ArrayList<Object> selectDao();
	Object selectTableById(int idx);
	ArrayList<String> selectTableAllList();
	String selectPasswdByEmail(String email);
	Object selectTableByEmail(String email);
	int updatePasswdByEmail(String email, String passwd);
	ArrayList<String> selectTableAllListByCode(int code); 
}
