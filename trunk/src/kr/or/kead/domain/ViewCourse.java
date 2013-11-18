package kr.or.kead.domain;

public class ViewCourse {
	private int code;
	private String departName;
	private String subject;
	private String material;
	private String profName;
	public ViewCourse(int code, String departName, String subject,
			String material, String profName) {
		this.code = code;
		this.departName = departName;
		this.subject = subject;
		this.material = material;
		this.profName = profName;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
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
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	@Override
	public String toString() {
		return String
				.format("ViewCourse [code=%s, departName=%s, subject=%s, material=%s, profName=%s]",
						code, departName, subject, material, profName);
	}
	
	
}
