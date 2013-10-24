package kr.or.kead.domain;

public class InfoSubject {
	private int code;
	private String name;
	private int prof;
	private String tel;
	
	public InfoSubject() {
		super();		
	}

	public InfoSubject(int code, String name, int prof, String tel) {
		this.code = code;
		this.name = name;
		this.prof = prof;
		this.tel = tel;
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

	public int getProf() {
		return prof;
	}

	public void setProf(int prof) {
		this.prof = prof;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String toString() {
		return String.format("InfoSubject [code=%s, name=%s, prof=%s, tel=%s]",
				code, name, prof, tel);
	}
	
}
