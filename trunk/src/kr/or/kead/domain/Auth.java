package kr.or.kead.domain;

public class Auth {	
	private String email;	
	private int level;
	
	public Auth(String email, int level) {
		this.email = email;
		this.level = level;
	}

	public String getEmail() {
		return email;
	}

	public int getLevel() {
		return level;
	}
	
	
	
	
}
