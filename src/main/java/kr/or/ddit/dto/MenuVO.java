package kr.or.ddit.dto;

public class MenuVO {
	
	private String mcode; // 메뉴 코드
	private String mname; // 메뉴 이름
	private String murl;  // 메뉴  url
	private String upcode; // 상위메뉴 코드
	
	
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMurl() {
		return murl;
	}
	public void setMurl(String murl) {
		this.murl = murl;
	}
	public String getUpcode() {
		return upcode;
	}
	public void setUpcode(String upcode) {
		this.upcode = upcode;
	}

	
	
}
