package kr.or.kead.domain;

public class RequestCourse {
	private int idx;
	private int std_idx;
	private int courseCode;
	private int grade;
		
	public RequestCourse(int idx, int std_idx, int courseCode, int grade) {
		this.idx = idx;
		this.std_idx = std_idx;
		this.courseCode = courseCode;
		this.grade = grade;
	}
	
	public RequestCourse(int std_idx, int courseCode) {		
		this.std_idx = std_idx;
		this.courseCode = courseCode;		
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getStd_idx() {
		return std_idx;
	}

	public void setStd_idx(int std_idx) {
		this.std_idx = std_idx;
	}

	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}




	@Override
	public String toString() {
		return String.format(
				"%s:%s:%s:%s", idx,
				std_idx, courseCode, grade);
	}
	
	
	
	
}
