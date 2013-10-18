package kr.or.kead.service;

import java.util.ArrayList;

import kr.or.kead.domain.InfoStudent;

public interface DaoTable {
	int insertDao(Object obj);
	int updateDao(Object obj);
	int deleteDao(int regNo);
	ArrayList<InfoStudent> selectDao();
	InfoStudent selectStudentById(int stdId);
}
