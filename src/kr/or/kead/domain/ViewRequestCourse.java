package kr.or.kead.domain;

public class ViewRequestCourse {
	private int idx;
	private int stdIdx;
	private String stdEmail;
	private int courseCode;
	private String courseSbj;
	private String material;
	private String departName;
	private String profName;
	private String profEmail;
	private int grade;
	public ViewRequestCourse(int idx, int stdIdx, String stdEmail,
			int courseCode, String courseSbj, String material,
			String departName, String profName, String profEmail, int grade) {
		this.idx = idx;
		this.stdIdx = stdIdx;
		this.stdEmail = stdEmail;
		this.courseCode = courseCode;
		this.courseSbj = courseSbj;
		this.material = material;
		this.departName = departName;
		this.profName = profName;
		this.profEmail = profEmail;
		this.grade = grade;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getStdIdx() {
		return stdIdx;
	}
	public void setStdIdx(int stdIdx) {
		this.stdIdx = stdIdx;
	}
	public String getStdEmail() {
		return stdEmail;
	}
	public void setStdEmail(String stdEmail) {
		this.stdEmail = stdEmail;
	}
	public int getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseSbj() {
		return courseSbj;
	}
	public void setCourseSbj(String courseSbj) {
		this.courseSbj = courseSbj;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	public String getProfEmail() {
		return profEmail;
	}
	public void setProfEmail(String profEmail) {
		this.profEmail = profEmail;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return String
				.format("%s:%s:%s:%s:%s:%s:%s:%s:%s:%s",
						idx, stdIdx, stdEmail, courseCode, courseSbj, material,
						departName, profName, profEmail, grade);
	}
	
	
}
