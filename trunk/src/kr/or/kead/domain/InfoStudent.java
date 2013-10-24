package kr.or.kead.domain;

import java.util.Date;

public class InfoStudent {
	private int idx;
	private String stdName;
	private String juminNum;
	private Date startDate;			// ���г⵵
	private Date endDate;			// ����⵵
	private String mobile;
	private String tel;
	private String stdAddr;
	private int roomNum;			// ����� ���� 500����, 200��, 300��, 400��
	private int stdType;			// �������
	private int grade;				// ��ֵ��
	private String email;
	private int departCode;
	
	
	public InfoStudent() {
		super();		
	}
	
	public InfoStudent(int idx, String stdName, String juminNum,
			Date startDate, Date endDate, String mobile, String tel,
			String stdAddr, int roomNum, int stdType, int grade, String email,
			int departCode) {
		this.idx = idx;
		this.stdName = stdName;
		this.juminNum = juminNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.mobile = mobile;
		this.tel = tel;
		this.stdAddr = stdAddr;
		this.roomNum = roomNum;
		this.stdType = stdType;
		this.grade = grade;
		this.email = email;
		this.departCode = departCode;
	}
	
	public InfoStudent(String stdName, String juminNum, Date startDate,
			Date endDate, String mobile, String tel, String stdAddr,
			int roomNum, int stdType, int grade, String email, int departCode) {
		this.stdName = stdName;
		this.juminNum = juminNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.mobile = mobile;
		this.tel = tel;
		this.stdAddr = stdAddr;
		this.roomNum = roomNum;
		this.stdType = stdType;
		this.grade = grade;
		this.email = email;
		this.departCode = departCode;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getJuminNum() {
		return juminNum;
	}

	public void setJuminNum(String juminNum) {
		this.juminNum = juminNum;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getStdAddr() {
		return stdAddr;
	}

	public void setStdAddr(String stdAddr) {
		this.stdAddr = stdAddr;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getStdType() {
		return stdType;
	}

	public void setStdType(int stdType) {
		this.stdType = stdType;
	}	
	
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	public int getDepartCode() {
		return departCode;
	}

	public void setDepartCode(int departCode) {
		this.departCode = departCode;
	}

	@Override
	public String toString() {
		return String
				.format("InfoStudent [idx=%s, stdName=%s, juminNum=%s, startDate=%s,"
						+ " endDate=%s, mobile=%s, tel=%s, stdAddr=%s, roomNum=%s, "
						+ "stdType=%s, grade=%s, email=%s, departCode=%s]",
						idx, stdName, juminNum, startDate, endDate, mobile,
						tel, stdAddr, roomNum, stdType, grade, email, departCode);
	}
	
	
	
}
