package kr.or.ddit.command;

import java.text.ParseException;
import java.util.Date;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.ReservationVO;

public class ResverationCommand {
	
	private Date res_date;
	private String book_no;
	private String id;
	private int book_status;
	
	
	public ReservationVO toRegist() throws Exception {
		
		ReservationVO resver = new ReservationVO();
		
		resver.setBook_no(book_no);
		resver.setId(id);
		resver.setBook_status(book_status);
		
		return resver;
	}
	
	
	
	public Date getRes_date() {
		return res_date;
	}
	public void setRes_date(Date res_date) {
		this.res_date = res_date;
	}
	public String getBook_no() {
		return book_no;
	}
	public void setBook_no(String book_no) {
		this.book_no = book_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBook_status() {
		return book_status;
	}
	public void setBook_status(int book_status) {
		this.book_status = book_status;
	}
	
	
}
