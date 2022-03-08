package kr.or.ddit.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.dto.MemberVO;

public class MemberModifyCommand {
	
	private String id;  //아이디
	private String name; //이름
	private String phone; //전화번호
	private String email;  //이메일
	private String authority; //권한
	private MultipartFile picture; // 사진파일 
	private String oldPicture; // 이전 사진파일명
	private String uploadPicture; // 변경된 사진 파일명.
	private String address;
	private String detail_address;
	private String birth_date;
	
	
	public MemberVO toParseMember() throws ParseException {
		MemberVO member = new MemberVO();
		member.setId(this.id);
		member.setName(this.name);
		member.setPhone(this.phone);
		member.setAddress(this.address);
		member.setEmail(this.email);
		member.setDetail_address(this.detail_address);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(this.birth_date);
		member.setBirth_date(date);
		
		return member;
	}
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public MultipartFile getPicture() {
		return picture;
	}


	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}


	public String getOldPicture() {
		return oldPicture;
	}


	public void setOldPicture(String oldPicture) {
		this.oldPicture = oldPicture;
	}


	public String getUploadPicture() {
		return uploadPicture;
	}


	public void setUploadPicture(String uploadPicture) {
		this.uploadPicture = uploadPicture;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getDetail_address() {
		return detail_address;
	}


	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}


	public String getBirth_date() {
		return birth_date;
	}


	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}


	
	
}
