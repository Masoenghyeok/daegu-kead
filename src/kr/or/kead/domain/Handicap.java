package kr.or.kead.domain;

public class Handicap {
	private int code;
	private String name;
	
	public Handicap(int code, String name) {
		this.code = code;
		this.name = name;
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

	@Override
	public String toString() {
		return String.format("%s-%s", code, name);
	}
	
	
	
}
