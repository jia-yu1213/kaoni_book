package kr.or.ddit.command;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.ReservationVO;

public class ResverationCommand {
	
	private Date res_date;
	private String book_no;
	private String id;
	private int book_status;
	private int res_status;
	
	public int getRes_status() {
		return res_status;
	}

	public void setRes_status(int res_status) {
		this.res_status = res_status;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	HttpSession session;

	public ReservationVO toRegist() throws Exception {
		
		
		ReservationVO res = new ReservationVO();
		
		res.setBook_no(book_no);
		res.setId(id);
		res.setBook_status(book_status);
		
		return res;
	}
	
	public ReservationVO toRemove() throws Exception{
		
		ReservationVO res = new ReservationVO();
		
		res.setBook_no(book_no);
		res.setId(id);
		res.setBook_status(book_status);
		
		return res;
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
