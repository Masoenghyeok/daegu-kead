package kr.or.kead.domain;

public class MemberInfo {
	private int idx;
	private String id;
	private String pass;
	private int level;
	
	public MemberInfo(int idx, String id, String pass, int level) {
		this.idx = idx;
		this.id = id;
		this.pass = pass;
		this.level = level;
	}
	
	public MemberInfo(String id, String pass, int level) {
		this.idx = idx;
		this.id = id;
		this.pass = pass;
		this.level = level;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return String.format("Member [idx=%s, id=%s, pass=%s, level=%s]", idx,
				id, pass, level);
	}	
}
