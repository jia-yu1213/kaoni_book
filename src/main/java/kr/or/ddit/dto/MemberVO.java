package kr.or.ddit.dto;

import java.util.Date;

public class MemberVO {
	
	private String name="---"; //이름
	private String id;  //아이디
	private String pwd; //패스워드
	private int gender;  //성별
	private String phone; //전화번호
	private String address;//주소
	private String email;  //이메일
	private Date last_login; //최종 로그인일자
	private String picture; // 사진파일 경로/파일명
	private Date regDate; // 등록일
	private String authority; // 권한
	private int enabled;   // 사용여부
	private String detail_address;//상세주소
	private Date birth_date; // 생년월일 

	
	public MemberVO() {}


	public MemberVO(String name, String id, String pwd, int gender, String phone, String address, String email,
			Date last_login, String picture, Date regDate, String authority, int enabled, String detail_address,
			Date birth_date) {
		super();
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.last_login = last_login;
		this.picture = picture;
		this.regDate = regDate;
		this.authority = authority;
		this.enabled = enabled;
		this.detail_address = detail_address;
		this.birth_date = birth_date;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public int getGender() {
		return gender;
	}


	public void setGender(int gender) {
		this.gender = gender;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getLast_login() {
		return last_login;
	}


	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public int getEnabled() {
		return enabled;
	}


	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}


	public String getDetail_address() {
		return detail_address;
	}


	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}


	public Date getBirth_date() {
		return birth_date;
	}


	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	
	
	

	
}




