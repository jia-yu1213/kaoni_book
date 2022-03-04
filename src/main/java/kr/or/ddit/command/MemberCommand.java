package kr.or.ddit.command;

import java.util.Date;

import kr.or.ddit.dto.MemberVO;

public class MemberCommand {
	
	private String id;
	private Date birth_date;
	
	
	public MemberVO toMemberDetail() throws Exception{
		MemberVO vo = new MemberVO();
		
		vo.setId(id);
		vo.setBirth_date(birth_date);
		
		return vo;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getBirth_date() {
		return birth_date;
	}


	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	
	
}
