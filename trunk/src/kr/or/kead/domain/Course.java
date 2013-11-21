
package kr.or.kead.domain;

public class Course {
	private int code;
	private int depart_code;
	private String subject;
	private String material;
	private int prof_code;
	public Course(int code, int depart_code, String subject, String material,
			int prof_code) {
		this.code = code;
		this.depart_code = depart_code;
		this.subject = subject;
		this.material = material;
		this.prof_code = prof_code;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getDepart_code() {
		return depart_code;
	}
	public void setDepart_code(int depart_code) {
		this.depart_code = depart_code;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public int getProf_code() {
		return prof_code;
	}
	public void setProf_code(int prof_code) {
		this.prof_code = prof_code;
	}
	
	@Override
	public String toString() {
		return String
				.format("Course [code=%s, depart_code=%s, subject=%s, material=%s, prof_code=%s]",
						code, depart_code, subject, material, prof_code);
	}
	
	
}
