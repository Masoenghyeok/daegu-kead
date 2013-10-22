package kr.or.kead.domain;

public class StdAddress {
	private int seq;
	private String zip_code;
	private String sido;
	private String gugun;
	private String dong;
	private String bunji;
	
	public StdAddress() {
		super();		
	}
	
	

	public StdAddress(String zip_code, String sido, String gugun, String dong,
			String bunji) {
		this.zip_code = zip_code;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.bunji = bunji;
	}



	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getBunji() {
		return bunji;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}
	
	
	

}
