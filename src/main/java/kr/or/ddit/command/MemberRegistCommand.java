package kr.or.ddit.command;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.util.CryptoUtil;

public class MemberRegistCommand {
	
	private String id;
	private String pwd;
	private String email;
	private String picture;
	private String authority;
	private String name;
	private String[] phone;
	private String address;
	private String detail_address;
	private String birth_date;
	
	
	public MemberVO toMemberVO() throws Exception {
		
		String phone="";
		for (String data : this.phone) {
			phone += data;
		}

		// MemberVO setting
		MemberVO member = new MemberVO();
		
		member.setName(name);
		member.setId(id);
		member.setPwd(pwd);
		member.setPhone(phone);
		member.setAddress(address);
		member.setEmail(email);
		member.setPicture(picture);
		member.setDetail_address(detail_address);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(birth_date);
		member.setBirth_date(date);
		
		return member;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String[] getPhone() {
		return phone;
	}


	public void setPhone(String[] phone) {
		this.phone = phone;
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