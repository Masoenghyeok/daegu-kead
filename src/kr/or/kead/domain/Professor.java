package kr.or.kead.domain;

public class Professor {
	private int code;
	private String name;
	private int depart;
	private String course;
	
	public Professor(int code, String name, int depart, String course) {
		this.code = code;
		this.name = name;
		this.depart = depart;
		this.course = course;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDepart() {
		return depart;
	}

	public void setDepart(int depart) {
		this.depart = depart;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getToString() {
		return depart+":"+name;
	}

	
	@Override
	public String toString() {
		return String.format(
				"Professor [code=%s, name=%s, depart=%s, course=%s]", code,
				name, depart, course);
	}	
}
